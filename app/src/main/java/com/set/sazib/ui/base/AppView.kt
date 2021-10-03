package com.set.sazib.ui.base

import com.androidnetworking.error.ANError

interface AppView {

  fun finishIt(): Unit?
  fun goBack(): Unit?
  fun showProgress(): Unit?
  fun hideProgress(): Unit?
  fun onError(errorCode: String?): Unit?
  fun handelError(aneError: ANError?): Unit?
}