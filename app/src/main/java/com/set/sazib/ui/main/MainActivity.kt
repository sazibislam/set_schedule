package com.set.sazib.ui.main

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.core.util.Supplier
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.set.sazib.R
import com.set.sazib.data.api.ApiService
import com.set.sazib.data.service.App
import com.set.sazib.databinding.ActivityDashboardBinding
import com.set.sazib.ui.base.BaseActivity
import com.set.sazib.ui.base.ViewModelProviderFactory
import com.set.sazib.ui.main.frag.property_list.SearchListFragment
import com.set.sazib.utils.view.ViewPagerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity : BaseActivity(), OnClickListener, CoroutineScope {

  @Inject lateinit var apiHelper: ApiService
  private lateinit var vm: MainVM
  private lateinit var binding: ActivityDashboardBinding

  private var job = Job()
  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

  companion object {
    const val TAG: String = "main_activity"
    const val CITY: String = "main_activity_city"
    const val ZIP: String = "main_activity_zip"
    const val ADDRESS: String = "main_activity_address"
    const val STATE: String = "main_activity_state"
    fun getStartIntent(
      context: Context,
      tag: String,
      city: String,
      zip: String,
      address: String,
      state: String
    ): Intent = Intent(context, MainActivity::class.java).apply {
      putExtra(TAG, tag)
      putExtra(CITY, city)
      putExtra(ZIP, zip)
      putExtra(ADDRESS, address)
      putExtra(STATE, state)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityDashboardBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initView()
  }

  private fun initView() {

    App.appComponent.inject(this@MainActivity)

    val supplier = Supplier { MainVM(apiHelper) }
    val factory = ViewModelProviderFactory(MainVM::class.java, supplier)
    vm = ViewModelProvider(this, factory).get(MainVM::class.java)

    binding.bottomNavigation.menu.getItem(2).isEnabled = false
    binding.bottomNavigation.selectedItemId = R.id.nav_fab

    val fragments: ArrayList<Fragment> = ArrayList()
    val tag: ArrayList<String> = ArrayList()

    val city = "${intent.getSerializableExtra(CITY)}"
    val zip = "${intent.getSerializableExtra(ZIP)}"
    val address = "${intent.getSerializableExtra(ADDRESS)}"
    val state = "${intent.getSerializableExtra(STATE)}"

    fragments.add(SearchListFragment.newInstance("$city", "$zip", "$address", "$state"))
    tag.add(SearchListFragment.TAG)

    val adapter = ViewPagerAdapter(this.supportFragmentManager)
    for (i: Int in fragments.indices) {
      adapter.addFragment(fragments[i], tag[i])
    }

    binding.vpHome.adapter = adapter

    binding.vpHome.setCurrentItem(0, false) //dashboard
    binding.vpHome.setPagingEnabled(false)

    binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
      when (item.itemId) {
        R.id.nav_search -> openFragment(0)
        R.id.nav_update -> openFragment(0)
        R.id.nav_saved_search -> openFragment(0)
        R.id.nav_home -> openFragment(0)
      }
      return@setOnNavigationItemSelectedListener true
    }

    binding.fab.setOnClickListener(this@MainActivity)
  }

  override fun onClick(v: View?) {

    when (v?.id) {
      binding.fab.id -> {
        ObjectAnimator.ofFloat(binding.fab, "rotation", 0f, 360f)
            .setDuration(800)
            .start()

        openFragment(0)
      }
    }
  }

  private fun openFragment(item: Int) {
    binding.vpHome.currentItem = item
  }

  override fun onDestroy() {
    job.cancel()
    super.onDestroy()
  }
}
