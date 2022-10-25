package com.example.repository.datasource

import com.example.model.data.AppState
import com.example.model.data.dto.SearchResultDto

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
    suspend fun getDataByWord(word: String): SearchResultDto?
}