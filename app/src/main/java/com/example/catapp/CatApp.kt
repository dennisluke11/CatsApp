package com.example.catapp

import android.app.Application
import com.example.catapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CatApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CatApp)
            modules(appModule)
        }
    }
}