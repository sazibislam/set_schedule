package com.set.sazib.ui.main.frag.property_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.os.bundleOf
import androidx.core.util.Supplier
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.set.sazib.R
import com.set.sazib.data.api.ApiService
import com.set.sazib.data.api.response.PropertyDataResponse
import com.set.sazib.data.service.App
import com.set.sazib.ui.base.BaseFragment
import com.set.sazib.ui.base.ViewModelProviderFactory
import com.set.sazib.ui.main.frag.property_list.adapter.FoldingCellListAdapter
import com.set.sazib.utils.Status.*
import com.set.sazib.utils.logger.AppLogger
import com.set.sazib.utils.view.folding.FoldingCell
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchListFragment : BaseFragment(), CoroutineScope {

  @Inject lateinit var apiHelper: ApiService

  private lateinit var vm: SearchListFragmentVM
  private val CITY = "search_list_city"
  private val ZIP = "search_list_zip"
  private val ADDRESS = "search_list_address"
  private val STATE = "search_list_state"
  private lateinit var propertyResponse: ArrayList<PropertyDataResponse>

  private var job = Job()
  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

  companion object {
    const val TAG = "search_frag"
    fun newInstance(
      city: String,
      zip: String,
      address: String,
      state: String
    ) = SearchListFragment().apply {
      arguments = bundleOf(
          TAG to tag,
          CITY to city,
          ZIP to zip,
          ADDRESS to address,
          STATE to state,
      )
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = inflater.inflate(R.layout.search_fragment, container, false)

  override fun setup(view: View) {

    val city = this.arguments?.getString(CITY)
    val address = this.arguments?.getString(ADDRESS)
    val state = this.arguments?.getString(STATE)
    val street = address?.replace("\\s".toRegex(), "+")
    val parameter = "street_name=$street&city=$city&state=$state"

    App.appComponent.inject(this@SearchListFragment)

    val supplier = Supplier { SearchListFragmentVM(apiHelper) }
    val factory = ViewModelProviderFactory(SearchListFragmentVM::class.java, supplier)
    vm = ViewModelProvider(this, factory).get(SearchListFragmentVM::class.java)

    propertyResponse = ArrayList<PropertyDataResponse>()
    launch(Dispatchers.IO) {
      vm.getProperty(parameter)
    }

    observeData()
  }

  private fun observeData() {

    vm.getPropertyResponseData()
        .observe(this, Observer {
          when (it.status) {
            SUCCESS -> {
              hideProgress()
              AppLogger.d(it.data)
              it.data?.let { data_ ->
                propertyResponse.add(data_)
                setupView()
                AppLogger.d(propertyResponse)
              }
            }
            LOADING -> {
              showProgress()
              Log.d(TAG, "Loading")
            }
            ERROR -> {
              hideProgress()
              onError(it.message)
            }
          }
        })
  }

  private fun setupView() {

    val adapter = FoldingCellListAdapter(context, propertyResponse)

    adapter.defaultRequestBtnClickListener = View.OnClickListener {
      AppLogger.d("success ")
    }

    mainListView?.adapter = adapter
    mainListView?.onItemClickListener =
      AdapterView.OnItemClickListener { adapterView_, view_, pos_, id_ ->

        // toggle clicked cell state
        val v = view_ as? FoldingCell
        v?.toggle(false)
        // register in adapter that state for selected cell is toggled
        adapter.registerToggle(pos_)
      }
  }

  override fun onDestroyView() {
    job.cancel()
    super.onDestroyView()
  }
}