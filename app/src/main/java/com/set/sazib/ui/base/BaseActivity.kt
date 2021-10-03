package com.set.sazib.ui.base

import android.app.Dialog
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.error.ANError
import com.set.sazib.R
import com.set.sazib.ui.base.BaseFragment.CallBack
import com.set.sazib.utils.Utils
import com.set.sazib.utils.logger.AppLogger

abstract class BaseActivity : AppCompatActivity(), AppView, CallBack {

  private var dialog: Dialog? = null

  override fun goBack() = super.onBackPressed()

  override fun finishIt() = finish()

  override fun onFragmentAttached() {

  }

  override fun onFragmentDetached(tag: String) {

  }

  fun showMsg(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
      .show()

  override fun showProgress() {
    hideProgress()
    dialog = Utils.showLoadingDialog(this)
  }

  override fun hideProgress() {
    dialog?.let { dialog_ -> if (dialog_.isShowing) dialog_.cancel() }
  }

  override fun onError(errorMsg: String?) {
    val defaultMsg = getString(R.string.something_error_happened)
    showMsg(errorMsg ?: defaultMsg)
    AppLogger.i("error-> $errorMsg")
  }

  override fun handelError(aneError: ANError?) {
    AppLogger.i("error-> ${aneError?.cause}")
  }

  private fun String.toSpanned(): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
      @Suppress("DEPRECATION") return Html.fromHtml(this)
    }
  }
}
