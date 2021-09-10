package com.example.tvmaze.ui.splash

import android.content.Intent
import android.os.Bundle
import com.example.tvmaze.ui.common.BaseActivity
import com.example.tvmaze.ui.schedule.ScheduleActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        goToSchedule()
    }

    private fun goToSchedule(){
        Thread.sleep(2000)
        startActivity(Intent(this, ScheduleActivity::class.java))
        finish()
    }

}