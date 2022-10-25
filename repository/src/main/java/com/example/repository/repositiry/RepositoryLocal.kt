package com.example.repository.repositiry

import com.example.model.data.AppState
import com.example.model.data.dto.SearchResultDto

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
    suspend fun getDataByWord(word: String): SearchResultDto?
}