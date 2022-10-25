package com.example.mydictionary.view.base

import androidx.appcompat.app.AppCompatActivity
import com.example.mydictionary.model.data.AppState
import com.example.mydictionary.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {
    /**В каждой Активити будет своя ViewModel, которая наследуется от BaseViewModel*/
    abstract val model: BaseViewModel<T>
    /**Каждая Активити будет отображать какие-то данные в соответствующем состоянии*/
    abstract fun renderData(appState: T)
}