package com.example.tvmaze

import android.app.Application
import android.content.Context

class App: Application() {

    companion object {

        lateinit var instance: App

        fun getContext(): Context = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}