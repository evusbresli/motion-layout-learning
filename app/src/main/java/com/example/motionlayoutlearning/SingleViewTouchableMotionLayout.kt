package com.example.motionlayoutlearning

import android.annotation.*
import android.content.*
import android.graphics.*
import android.util.*
import android.view.*
import androidx.constraintlayout.motion.widget.*

class SingleViewTouchableMotionLayout(context: Context, attrs: AttributeSet?) : MotionLayout(context, attrs) {

    private val viewToDetectTouch by lazy { findViewById<View>(R.id.playerBackground) }
    private val viewRect = Rect()

    private var touchStarted = false

    init {
        setTransitionListener(object : TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) = Unit

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) =
                Unit

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                touchStarted = false
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) = Unit
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                touchStarted = false
                return super.onTouchEvent(event)
            }
        }
        if (!touchStarted) {
            viewToDetectTouch?.getHitRect(viewRect)
            touchStarted = viewRect.contains(event.x.toInt(), event.y.toInt())
        }

        return touchStarted and super.onTouchEvent(event)
    }
}