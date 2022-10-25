package com.example.mydictionary.model.datasource

/** Источник данных для репозитория (Интернет, БД и т. п.)*/
interface DataSource<T> {
    suspend fun getData(word: String): T
}