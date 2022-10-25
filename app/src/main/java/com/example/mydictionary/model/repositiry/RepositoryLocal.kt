package com.example.mydictionary.model.repositiry

import com.example.mydictionary.model.data.AppState
import com.example.mydictionary.model.data.DataModel

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
    suspend fun getDataByWord(word: String): DataModel?
}