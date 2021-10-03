package com.set.sazib.di.component

import android.app.Application
import com.set.sazib.data.service.App
import com.set.sazib.di.module.AppModule
import com.set.sazib.ui.find_property.PropertyFindActivity
import com.set.sazib.ui.main.MainActivity
import com.set.sazib.ui.main.frag.property_list.SearchListFragment
import com.set.sazib.ui.splash.SplashActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [(AndroidInjectionModule::class), (AppModule::class)]
)
interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance fun application(application: Application): Builder
    fun build(): AppComponent
  }

  fun inject(app: App)
  fun inject(splashActivity: SplashActivity)
  fun inject(propertyFindActivity: PropertyFindActivity)
  fun inject(mainActivity: MainActivity)

  //fragment
  fun inject(searchListFragment: SearchListFragment)
}