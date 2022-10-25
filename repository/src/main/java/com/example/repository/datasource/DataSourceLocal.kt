package com.example.repository.datasource

import com.example.model.data.AppState
import com.example.model.data.DataModel

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
    suspend fun getDataByWord(word: String): DataModel?
}