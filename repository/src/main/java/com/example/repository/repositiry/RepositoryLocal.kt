package com.example.repository.repositiry

import com.example.model.data.AppState
import com.example.model.data.DataModel

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
    suspend fun getDataByWord(word: String): DataModel?
}