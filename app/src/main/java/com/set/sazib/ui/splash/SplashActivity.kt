package com.set.sazib.ui.splash

import android.os.Bundle
import com.set.sazib.data.service.App
import com.set.sazib.databinding.ActivitySplashBinding
import com.set.sazib.ui.base.BaseActivity
import com.set.sazib.ui.find_property.PropertyFindActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : BaseActivity() {

  private lateinit var binding: ActivitySplashBinding
  private val SPLASH_TIME = 3000L

  companion object {
    private const val TAG = "splash_activity"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivitySplashBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initView()
  }

  private fun initView() {

    App.appComponent.inject(this@SplashActivity)
    Timer().schedule(SPLASH_TIME) {
      startActivity(PropertyFindActivity.getStartIntent(this@SplashActivity, TAG))
      finishIt()
    }
  }
}