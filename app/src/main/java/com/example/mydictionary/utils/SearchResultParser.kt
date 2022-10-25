package com.example.mydictionary.utils

import com.example.model.data.AppState
import com.example.model.data.dto.SearchResultDto
import com.example.model.data.userdata.DataModel
import com.example.model.data.userdata.Meaning
import com.example.model.data.userdata.TranslatedMeaning


/**  Все методы говорят сами за себя, универсальны и парсят данные в зависимости от источника данных
 *  (интернет или БД), возвращая их в понятном для наших Activity форматах */
fun mapSearchResultToResult(searchResults: List<SearchResultDto>): List<DataModel> {
    return searchResults.map { searchResult ->
        var meanings: List<Meaning> = listOf()
        searchResult.meanings?.let {
            // Дополнительная проверка для HistoryScreen, так как там сейчас не отображаются значения
            meanings = it.map { meaningsDto ->
                Meaning(
                    TranslatedMeaning(meaningsDto.translation?.translation ?: ""),
                    meaningsDto.imageUrl ?: "",
                    meaningsDto.transcription ?: ""
                )
            }
        }
        DataModel(searchResult.text ?: "", meanings)
    }
}

fun mapSearchResultToDataModel(searchResult: SearchResultDto): DataModel {
    var meanings: List<Meaning> = listOf()
    searchResult.meanings?.let {
        // Дополнительная проверка для HistoryScreen, так как там сейчас не отображаются значения
        meanings = it.map { meaningsDto ->
            Meaning(
                TranslatedMeaning(meaningsDto.translation?.translation ?: ""),
                meaningsDto.imageUrl ?: "",
                meaningsDto.transcription ?: ""
            )
        }
    }
    return DataModel(searchResult.text ?: "", meanings)
}

fun parseOnlineSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, true))
}

private fun mapResult(appState: AppState, isOnline: Boolean): List<DataModel> {
    val newSearchResults = arrayListOf<DataModel>()
    when (appState) {
        is AppState.Success -> {
            getSuccessResultData(appState, isOnline, newSearchResults)
        }
        else -> {}
    }
    return newSearchResults
}

private fun getSuccessResultData(
    appState: AppState.Success,
    isOnline: Boolean,
    newSearchDataModels: ArrayList<DataModel>
) {
    val searchDataModels: List<DataModel> = appState.data as List<DataModel>
    if (searchDataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in searchDataModels) {
                parseOnlineResult(searchResult, newSearchDataModels)
            }
        } else {
            for (searchResult in searchDataModels) {
                newSearchDataModels.add(DataModel(searchResult.text, arrayListOf()))
            }
        }
    }
}

private fun parseOnlineResult(
    searchDataModel: DataModel,
    newSearchDataModels: ArrayList<DataModel>
) {
    if (searchDataModel.text.isNotBlank() && searchDataModel.meanings.isNotEmpty()) {
        val newMeanings = arrayListOf<Meaning>()
        newMeanings.addAll(searchDataModel.meanings.filter {
            it.translatedMeaning.translatedMeaning.isNotBlank()
        })
        if (newMeanings.isNotEmpty()) {
            newSearchDataModels.add(
                DataModel(searchDataModel.text, newMeanings)
            )
        }
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

fun convertMeaningsTranscriptionToString(meanings: List<Meaning>): String {
    return "[${meanings[0].transcription}]"
}