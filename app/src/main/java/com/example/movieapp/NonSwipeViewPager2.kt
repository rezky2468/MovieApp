package com.example.movieapp

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager2.widget.ViewPager2

//class NonSwipeViewPager2 @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null
//) : ViewPager2(context, attrs) {
//    private var isSwipeEnabled = false
//
//    fun setSwipeEnabled(enabled: Boolean) {
//        isSwipeEnabled = enabled
//    }
//
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        return isSwipeEnabled && super.onTouchEvent(event)
//    }
//
//    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
//        return isSwipeEnabled && super.onInterceptTouchEvent(event)
//    }
//}