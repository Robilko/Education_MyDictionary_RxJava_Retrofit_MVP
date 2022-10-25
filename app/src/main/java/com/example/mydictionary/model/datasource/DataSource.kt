package com.example.mydictionary.model.datasource

import io.reactivex.Observable

// Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSource<T> {
    fun getData(word: String): Observable<T>
}