package com.example.mydictionary.model.datasource

import com.example.mydictionary.model.data.DataModel
import com.example.mydictionary.model.datasource.DataSource
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }
}