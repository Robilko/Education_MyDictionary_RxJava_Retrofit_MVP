package com.example.mydictionary.application

import android.app.Application
import com.example.mydictionary.di.application
import com.example.mydictionary.di.mainScreen
import org.koin.core.context.startKoin

class MyDictionaryApp : Application() {

    /**Инициализируем Koin в приложении*/
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}