package com.example.repository.utils

import com.example.model.data.AppState
import com.example.model.data.dto.SearchResultDto
import com.example.model.data.dto.MeaningsDto
import com.example.model.data.dto.TranslationDto
import com.example.model.data.userdata.Meaning
import com.example.repository.room.HistoryEntity

/** Принимаем на вход список слов в виде таблицы из БД и переводим его в List<SearchResult> */
fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<SearchResultDto> {
    val searchResult = ArrayList<SearchResultDto>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(SearchResultDto(entity.word, null))
        }
    }
    return searchResult
}

/** Метод конвертирует полученный от сервера результат в данные, доступные для сохранения в БД */
fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isEmpty()) {
                null
            } else {
                HistoryEntity(
                    searchResult[0].text,
                    convertMeaningsTranslationToString(searchResult[0].meanings),
                    searchResult[0].meanings[0].imageUrl,
                    searchResult[0].meanings[0].transcription
                )
            }
        }
        else -> null
    }
}


fun convertMeaningsTranslationToString(meanings: List<Meaning>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translatedMeaning.translatedMeaning, ", ")
        } else {
            meaning.translatedMeaning.translatedMeaning
        }
    }
    return meaningsSeparatedByComma
}


fun convertHistoryEntityToDataModel(entity: HistoryEntity): SearchResultDto {
    val meanings = arrayListOf<MeaningsDto>()
    meanings.add(
        MeaningsDto(
            TranslationDto(entity.description),
            entity.imageUrl,
            entity.transcription
        )
    )
    return SearchResultDto(entity.word, meanings)
}