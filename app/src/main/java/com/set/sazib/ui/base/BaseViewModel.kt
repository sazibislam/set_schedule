package com.set.sazib.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
  var mCompositeDisposable = CompositeDisposable()

  override fun onCleared() {
    super.onCleared()
    mCompositeDisposable.dispose()
  }
}