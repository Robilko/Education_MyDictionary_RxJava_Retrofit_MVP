package com.example.mydictionary.model.repositiry

import io.reactivex.Observable

// Репозиторий представляет собой слой получения и хранения данных, которые он
// передаёт интерактору

interface Repository<T> {
    fun getData(word: String): Observable<T>
}