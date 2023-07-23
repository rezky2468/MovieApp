package com.example.movieapp

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

class CustomBottomSheetBehavior(context: Context, attrs: AttributeSet) :
    BottomSheetBehavior<View>(context, attrs) {

    private var initialY = 0f

    override fun onInterceptTouchEvent(parent: CoordinatorLayout, child: View, event: MotionEvent): Boolean {
        // Intercept touch events to allow scrolling in the ScrollView when the BottomSheet is fully expanded
        if (state == STATE_EXPANDED) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> initialY = event.rawY
                MotionEvent.ACTION_MOVE -> {
                    val deltaY = event.rawY - initialY
                    if (deltaY > 0 && child.top == parent.height - peekHeight) {
                        return false
                    }
                }
            }
        }
        return super.onInterceptTouchEvent(parent, child, event)
    }
}