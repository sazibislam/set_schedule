package com.set.sazib.data.api

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.set.sazib.data.api.response.PropertyDataResponse
import io.reactivex.Observable
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val mApiHeader: ApiHeader) : ApiService {

  override fun getApiHeader(): ApiHeader = mApiHeader

  val token = "kqpeFY41ELMtbtXwjeCKGkARe6MTjw"
  var ENDPOINT_TEST = "https://apis.estated.com/v4/property?token=$token&"

  override fun getPropertyDataData(parameters: String): Observable<PropertyDataResponse> {
    return Rx2AndroidNetworking.get(ENDPOINT_TEST + parameters)
//        .addHeaders(mApiHeader.authApiHeader)
        .build()
        .getObjectObservable(PropertyDataResponse::class.java)
  }
}