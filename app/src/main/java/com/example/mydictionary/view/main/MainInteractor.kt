package com.example.mydictionary.view.main

import com.example.model.data.AppState
import com.example.model.data.DataModel
import com.example.repository.repositiry.Repository
import com.example.repository.repositiry.RepositoryLocal
import com.example.core.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {
    /** Теперь полученное слово мы сохраняем в БД. Сделать это нужно именно
     * здесь, в соответствии с принципами чистой архитектуры: интерактор обращается к репозиторию */
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }

    suspend  fun getDataByWord(word: String): DataModel? {
        return repositoryLocal.getDataByWord(word)
    }
}