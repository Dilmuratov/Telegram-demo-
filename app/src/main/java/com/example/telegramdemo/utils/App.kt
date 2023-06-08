package com.example.telegramdemo.utils

import android.app.Application
import com.example.telegramdemo.di.appModule
import com.example.telegramdemo.di.dataModule
import com.example.telegramdemo.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(appModule, dataModule, networkModule))
            androidContext(this@App)
        }
    }
}