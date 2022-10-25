package com.example.repository.utils

import com.example.model.data.AppState
import com.example.model.data.DataModel
import com.example.model.data.Meanings
import com.example.model.data.Translation
import com.example.repository.room.HistoryEntity

/** Принимаем на вход список слов в виде таблицы из БД и переводим его в List<SearchResult> */
fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModel> {
    val dataModel = ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            dataModel.add(DataModel(entity.word, null))
        }
    }
    return dataModel
}

/** Метод конвертирует полученный от сервера результат в данные, доступные для сохранения в БД */
fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(
                    searchResult[0].text!!,
                    convertMeaningsTranslationToString(searchResult[0].meanings!!),
                    searchResult[0].meanings!![0].imageUrl,
                    searchResult[0].meanings!![0].transcription
                )
            }
        }
        else -> null
    }
}


fun convertMeaningsTranslationToString(meanings: List<Meanings>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}


fun convertHistoryEntityToDataModel(entity: HistoryEntity): DataModel {
    val meanings = arrayListOf<Meanings>()
    meanings.add(
        Meanings(
            Translation(entity.word),
            entity.imageUrl,
            entity.transcription
        )
    )
    return DataModel(entity.word, meanings)
}