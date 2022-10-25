package com.example.mydictionary.model.repositiry

import com.example.mydictionary.model.data.DataModel
import com.example.mydictionary.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    /** Репозиторий возвращает данные, используя dataSource (локальный или внешний)*/
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}