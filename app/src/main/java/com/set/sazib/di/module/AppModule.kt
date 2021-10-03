package com.set.sazib.di.module

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import com.readystatesoftware.chuck.ChuckInterceptor
import com.set.sazib.BuildConfig
import com.set.sazib.data.api.ApiHeader
import com.set.sazib.data.api.ApiService
import com.set.sazib.data.api.AppApiHelper
import com.set.sazib.di.*
import com.set.sazib.utils.AppAuth.keyHash
import com.set.sazib.utils.AppConstants
import com.set.sazib.utils.CertSHA1
import com.set.sazib.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

  @Provides @Singleton internal fun provideContext(application: Application): Context = application

  @Provides @AppKey
  internal fun provideAppKey(context: Context): String =
    CertSHA1.getCertificateSHA1(context.applicationContext)

  @Provides @PackageName internal fun providePackageName(): String = BuildConfig.APPLICATION_ID

  @Provides @VersionName internal fun provideVersionName(): String = BuildConfig.VERSION_NAME

  @Provides @KeyHash internal fun provideKeyHash(): String = keyHash()

  @Provides @ContentType internal fun provideContentType(): String = BuildConfig.CONTENT_TYPE

  @Provides @Singleton internal fun provideProtectedApiHeader(
    @AppKey app_key: String,
    @PackageName packageName: String,
    @VersionName versionName: String,
    @ContentType contentType: String,
    @KeyHash keyHash: String
  ): ApiHeader.AuthApiHeader = ApiHeader.AuthApiHeader(
      _content_type = contentType,
      _key_hash = keyHash
  )

  @Provides @Singleton internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiService =
    appApiHelper

  @Provides @Singleton internal fun provideOkHttpClient(context: Context): OkHttpClient {
    val cacheSize = (5 * 1024 * 1024).toLong() // 5mb cache
    val myCache = Cache(ContextCompat.getCodeCacheDir(context), cacheSize)
    val builder = OkHttpClient().newBuilder()
    builder.cache(myCache)
    if (BuildConfig.DEBUG) {
      builder.addInterceptor(ChuckInterceptor(context))
//      builder.addNetworkInterceptor(CacheInterceptor(myCache))
//      builder.addNetworkInterceptor(StethoInterceptor())
    }
    builder.connectTimeout(AppConstants.TIMEOUT_CONNECTION, TimeUnit.SECONDS)
    builder.readTimeout(AppConstants.TIMEOUT_READ, TimeUnit.SECONDS)
    builder.writeTimeout(AppConstants.TIMEOUT_WRITE, TimeUnit.SECONDS)
    return builder.build()
  }

  @Provides internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

  @Provides internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()
}