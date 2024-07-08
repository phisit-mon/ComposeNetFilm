package com.phisit.composenetfilm

import android.app.Application
import com.phisit.composenetfilm.di.dataModule
import com.phisit.composenetfilm.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(presentationModule)
            modules(dataModule)
        }
    }
}