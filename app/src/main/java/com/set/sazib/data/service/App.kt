package com.set.sazib.data.service

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.set.sazib.di.component.AppComponent
import com.set.sazib.di.component.DaggerAppComponent
import com.set.sazib.utils.logger.AppLogger
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import okhttp3.OkHttpClient
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

  @Inject internal lateinit var okHttpClient: OkHttpClient
  @Inject internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

  override fun androidInjector(): AndroidInjector<Any> = activityDispatchingAndroidInjector

  companion object {
    private var mInstance: App? = null
    lateinit var appComponent: AppComponent

    @Synchronized fun getInstance(): App {
      if (mInstance == null) mInstance = App()
      return mInstance as App
    }
  }

  override fun onCreate() {
    super.onCreate()
    mInstance = this

   AppLogger.init()

    appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

    appComponent.inject(this)

//    Stetho.initializeWithDefaults(this)
    AndroidNetworking.initialize(applicationContext, okHttpClient)

    AppLogger.d("_sazib", "onCreate called")

  }
}