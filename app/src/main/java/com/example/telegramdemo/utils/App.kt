package com.example.telegramdemo.utils

import android.app.Application
import android.content.Context

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val appContext: Context = this
    }
}