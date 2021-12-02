package com.example.onlinecinema

import android.app.Application
import com.example.onlinecinema.di.appModule
import com.example.onlinecinema.di.ciceroneModule
import com.example.onlinecinema.di.playerModule
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(appModule, playerModule, ciceroneModule)
        }
    }
}