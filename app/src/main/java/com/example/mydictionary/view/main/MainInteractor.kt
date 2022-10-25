package com.example.mydictionary.view.main

import com.example.mydictionary.di.NAME_LOCAL
import com.example.mydictionary.di.NAME_REMOTE
import com.example.mydictionary.model.data.AppState
import com.example.mydictionary.model.data.DataModel
import com.example.mydictionary.presenter.Interactor
import com.example.mydictionary.model.repositiry.Repository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    /** Снабжаем интерактор репозиторием для получения локальных или внешних данных*/
    @Named(NAME_REMOTE) val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {
    /** Интерактор лишь запрашивает у репозитория данные, детали имплементации интерактору неизвестны*/
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}