package com.set.sazib.utils.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomViewPager : ViewPager {

  private var isPagingEnabled = true

  constructor(context: Context) : super(context)

  constructor(
    context: Context,
    attrs: AttributeSet
  ) : super(context, attrs)

  @SuppressLint("ClickableViewAccessibility")
  override fun onTouchEvent(event: MotionEvent): Boolean {
    return this.isPagingEnabled && super.onTouchEvent(event)
  }

  override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
    return this.isPagingEnabled && super.onInterceptTouchEvent(event)
  }

  fun setPagingEnabled(b: Boolean) {
    this.isPagingEnabled = b
  }
}
