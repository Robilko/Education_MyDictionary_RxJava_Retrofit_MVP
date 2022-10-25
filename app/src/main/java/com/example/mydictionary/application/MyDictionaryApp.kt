package com.example.mydictionary.application

import android.app.Application
import com.example.mydictionary.di.components.AppComponent
import com.example.mydictionary.di.components.DaggerAppComponent

class MyDictionaryApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder()
            .setContext(this)
            .build()
    }

    companion object {
        lateinit var instance: MyDictionaryApp
    }


}