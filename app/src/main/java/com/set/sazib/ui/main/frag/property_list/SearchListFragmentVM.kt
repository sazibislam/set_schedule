package com.set.sazib.ui.main.frag.property_list

import androidx.lifecycle.MutableLiveData
import com.set.sazib.data.api.ApiService
import com.set.sazib.data.api.response.PropertyDataResponse
import com.set.sazib.ui.base.BaseViewModel
import com.set.sazib.utils.Resource
import com.set.sazib.utils.logger.AppLogger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchListFragmentVM @Inject constructor(private val apiHelper: ApiService) : BaseViewModel() {

  private val propertyResponse = MutableLiveData<Resource<PropertyDataResponse>>()

  fun getProperty(parameter: String) {

    propertyResponse.postValue(Resource.loading(null))

    mCompositeDisposable.add(
        apiHelper.getPropertyDataData(parameter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data_ ->
              propertyResponse.postValue(Resource.success(data_))
            }, { e_ ->
              AppLogger.i("response  ${e_.message}")
              propertyResponse.postValue(
                  Resource.error(e_.message ?: "Something Went Wrong", null, null)
              )
            })
    )
  }

  fun getPropertyResponseData(): MutableLiveData<Resource<PropertyDataResponse>> = propertyResponse

}