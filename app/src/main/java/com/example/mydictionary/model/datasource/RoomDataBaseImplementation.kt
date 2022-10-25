package com.example.mydictionary.model.datasource

import com.example.mydictionary.model.data.AppState
import com.example.mydictionary.model.data.DataModel
import com.example.mydictionary.room.HistoryDao
import com.example.mydictionary.utils.convertDataModelSuccessToEntity
import com.example.mydictionary.utils.convertHistoryEntityToDataModel
import com.example.mydictionary.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }
    /** Метод сохранения слова в БД. Он будет использоваться в интеракторе */
    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getDataByWord(word: String): DataModel? {
        val entity = historyDao.getDataByWord(word)
        return if (entity != null) {
            convertHistoryEntityToDataModel(entity)
        } else {
            return null
        }
    }
}