package com.example.tvmaze.utils

import android.util.TypedValue
import com.example.tvmaze.App

class ViewUtils {

    companion object {

        fun dpToPx(dp: Float): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, App.getContext().resources.displayMetrics)
        }
    }
}