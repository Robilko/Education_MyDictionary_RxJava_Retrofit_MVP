package com.example.repository.repositiry

import com.example.model.data.AppState
import com.example.model.data.DataModel
import com.example.repository.datasource.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getDataByWord(word: String): DataModel? {
        return dataSource.getDataByWord(word)
    }
}