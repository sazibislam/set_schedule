package com.set.sazib.ui.find_property

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.core.util.Supplier
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.set.sazib.BuildConfig
import com.set.sazib.R
import com.set.sazib.data.api.ApiService
import com.set.sazib.data.service.App
import com.set.sazib.databinding.ActivityPropertyFindBinding
import com.set.sazib.ui.base.BaseActivity
import com.set.sazib.ui.base.ViewModelProviderFactory
import com.set.sazib.ui.main.MainActivity
import com.set.sazib.utils.NetworkUtils.isNetworkConnected
import com.set.sazib.utils.Status.*
import com.set.sazib.utils.logger.AppLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PropertyFindActivity : BaseActivity(), OnClickListener, CoroutineScope {

  @Inject lateinit var apiHelper: ApiService
  private lateinit var vm: PropertyFindActivityVM
  private lateinit var binding: ActivityPropertyFindBinding

  private var job = Job()
  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

  companion object {
    const val TAG: String = "login_activity"
    fun getStartIntent(
      context: Context,
      tag: String
    ): Intent = Intent(context, PropertyFindActivity::class.java).apply {
      putExtra(TAG, tag)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityPropertyFindBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initView()
    observeData()
  }

  private fun observeData() {

    vm.getPropertyResponseData()
        .observe(this, Observer {
          when (it.status) {
            SUCCESS -> {
              hideProgress()
              AppLogger.d(it.data)
              startActivity(
                  MainActivity.getStartIntent(
                      context = this@PropertyFindActivity,
                      tag = TAG,
                      city = "${binding.etCity.text}",
                      zip = "${binding.etZip.text}",
                      address = "${binding.etStreetAddress.text}",
                      state = "${binding.etState.text}",
                  )
              )
              finish()
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

  private fun initView() {

    App.appComponent.inject(this@PropertyFindActivity)
    binding.cvLogin.setOnClickListener(this@PropertyFindActivity)

    val supplier = Supplier { PropertyFindActivityVM(apiHelper) }
    val factory = ViewModelProviderFactory(PropertyFindActivityVM::class.java, supplier)
    vm = ViewModelProvider(this, factory).get<PropertyFindActivityVM>(
        PropertyFindActivityVM::class.java
    )

    if (BuildConfig.DEBUG) {
      binding.etCity.setText("Rochester")
      binding.etZip.setText("14624")
      binding.etState.setText("NY")
      binding.etStreetAddress.setText("151 Battle Green")
    }
  }

  override fun onClick(v: View?) {

    when (v?.id) {

      binding.cvLogin.id -> {

        if (isNetworkConnected(this@PropertyFindActivity))
          launch(Dispatchers.IO) {
            val street = binding.etStreetAddress.text.replace("\\s".toRegex(), "+")
            val parameter = "street_name=$street&city=${binding.etCity.text}&state=${binding.etState.text}"
            vm.getProperty(parameter)
          }
        else showMsg(getString(R.string.no_internet))
      }
    }
  }

  override fun onDestroy() {
    job.cancel()
    super.onDestroy()
  }
}