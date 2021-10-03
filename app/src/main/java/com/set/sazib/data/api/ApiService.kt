package com.set.sazib.data.api

import com.set.sazib.data.api.response.PropertyDataResponse
import io.reactivex.Observable

interface ApiService {

  fun getApiHeader(): ApiHeader
  fun getPropertyDataData(parameters: String): Observable<PropertyDataResponse>
}