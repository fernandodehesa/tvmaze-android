package com.example.tvmaze.ui.common

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tvmaze.R
import com.example.tvmaze.utils.AppUtils

open class BaseActivity: AppCompatActivity() {

    protected lateinit var spinner: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        spinner = AppUtils.showSpinnerDialog(this)

        setOrientation()
    }

    private fun setOrientation(){
        if(resources.getBoolean(R.bool.portrait_only)){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

}