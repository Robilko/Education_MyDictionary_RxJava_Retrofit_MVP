package com.example.repository.datasource

import com.example.model.data.AppState
import com.example.model.data.dto.SearchResultDto
import com.example.repository.room.HistoryDao
import com.example.repository.utils.convertDataModelSuccessToEntity
import com.example.repository.utils.convertHistoryEntityToDataModel
import com.example.repository.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }
    /** Метод сохранения слова в БД. Он будет использоваться в интеракторе */
    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getDataByWord(word: String): SearchResultDto? {
        val entity = historyDao.getDataByWord(word)
        return if (entity != null) {
            convertHistoryEntityToDataModel(entity)
        } else {
            return null
        }
    }
}