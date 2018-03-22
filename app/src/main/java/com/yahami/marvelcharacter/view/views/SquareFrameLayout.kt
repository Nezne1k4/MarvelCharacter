package com.yahami.marvelcharacter.view.views

import android.util.AttributeSet
import android.content.Context
import android.widget.FrameLayout

/**
 * Used https://kotlinlang.org/docs/reference/java-to-kotlin-interop.html#overloads-generation
 * '@JvmOverloads constructor' helps to generate multi constructor as input parameters in java code
 * If in java, we must declare 3 different constructor with overloading parameters
 */
class SquareFrameLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // forcing the element to always have the same height as width
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}