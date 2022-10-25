package com.example.mydictionary.model.datasource

import com.example.mydictionary.model.data.AppState
import com.example.mydictionary.model.data.DataModel

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
    suspend fun getDataByWord(word: String): DataModel?
}