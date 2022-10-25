package com.example.mydictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydictionary.model.data.AppState
import com.example.mydictionary.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**Создадим базовую ViewModel, куда вынесем общий для всех функционал*/
abstract class BaseViewModel<T : AppState>(
    /** Обратите внимание, что мы добавили инстанс LiveData*/
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : ViewModel() {
    /** Метод, благодаря которому Activity подписывается на изменение данных,
     возвращает LiveData, через которую и передаются данные*/
    open fun getData(word: String, isOnline: Boolean): LiveData<T> = liveDataForViewToObserve
    /**Единственный метод класса ViewModel, который вызывается перед уничтожением Activity*/
    override fun onCleared() {
        compositeDisposable.clear()
    }
}