package com.example.tvmaze.ui.common.header

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.example.tvmaze.R
import com.example.tvmaze.utils.ViewUtils

open class Header(context: Context, attrs: AttributeSet?): LinearLayout(context, attrs) {

    private val activity: Activity = context as Activity

    protected fun setupLayout(){
        orientation = HORIZONTAL
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            ViewUtils.dpToPx(55f).toInt()
        )
        gravity = Gravity.CENTER_VERTICAL
        setPadding(ViewUtils.dpToPx(5f).toInt())
        setBackgroundColor(ContextCompat.getColor(context, R.color.black))
    }

    protected fun onBackClick(){
        activity.onBackPressed()
    }
}