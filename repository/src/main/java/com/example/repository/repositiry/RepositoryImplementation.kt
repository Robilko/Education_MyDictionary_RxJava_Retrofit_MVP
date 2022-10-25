package com.example.repository.repositiry

import com.example.model.data.DataModel
import com.example.repository.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    /** Репозиторий возвращает данные, используя dataSource (локальный или внешний)*/
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}