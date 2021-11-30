package com.example.onlinecinema

import android.app.Application
import com.example.onlinecinema.di.appModule
import com.example.onlinecinema.di.playerModule
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {


    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(appModule, playerModule)
        }
    }
}