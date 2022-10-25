package com.example.historyscreen

import com.example.model.data.AppState
import com.example.core.viewmodel.Interactor
import com.example.historyscreen.utils.mapSearchResultToResult
import com.example.model.data.dto.SearchResultDto
import com.example.repository.repositiry.Repository
import com.example.repository.repositiry.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }
}