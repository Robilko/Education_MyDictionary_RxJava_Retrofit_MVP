package com.example.mydictionary.di

import com.example.mydictionary.model.data.DataModel
import com.example.mydictionary.model.datasource.RetrofitImplementation
import com.example.mydictionary.model.datasource.RoomDataBaseImplementation
import com.example.mydictionary.model.repositiry.Repository
import com.example.mydictionary.model.repositiry.RepositoryImplementation
import com.example.mydictionary.view.main.MainInteractor
import com.example.mydictionary.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**Для удобства создадим две переменные: в одной находятся зависимости, используемые во всём приложении, во второй - зависимости конкретного экрана.
 *
 * Функция single сообщает Koin, что эта зависимость должна храниться в виде синглтона (в Dagger есть похожая аннотация) Аннотация named выполняет аналогичную Dagger функцию*/

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(RoomDataBaseImplementation())
    }
}

/**Функция factory сообщает Koin, что эту зависимость нужно создавать каждый раз заново, что как раз подходит для Activity и её компонентов.*/
val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}