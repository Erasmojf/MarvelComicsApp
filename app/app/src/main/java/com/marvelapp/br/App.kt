package com.marvelapp.br

import android.app.Application
import com.marvelapp.br.config.remoteModule
import com.marvelapp.br.config.repoModule
import com.marvelapp.br.config.uiModule
import org.koin.android.ext.koin.androidContext

import org.koin.core.context.startKoin

open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
           modules(uiModule, remoteModule, repoModule).androidContext(this@App)
        }
    }
}