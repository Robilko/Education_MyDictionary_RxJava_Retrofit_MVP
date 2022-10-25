package com.example.mydictionary.view.base

import com.example.mydictionary.model.data.AppState

// Нижний уровень. View знает о контексте и фреймворке
interface BaseView {
// View имеет только один метод, в который приходит некое состояние приложения

    fun renderData(appState: AppState)
}