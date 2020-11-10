package com.sindrenm.spikes.compose.login

import android.app.Application
import android.content.SharedPreferences

class App : Application() {
    companion object {
        lateinit var sharedPreferences: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()

        sharedPreferences = getSharedPreferences("auth", MODE_PRIVATE)
    }
}
