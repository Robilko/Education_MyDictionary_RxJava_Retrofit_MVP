package com.example.mydictionary.model.datasource

import com.example.mydictionary.model.data.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /**внимание, метод теперь возвращает Deferred. В rx был Observer*/
    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}