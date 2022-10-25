package com.example.mydictionary.di

import androidx.room.Room
import com.example.mydictionary.model.data.DataModel
import com.example.mydictionary.model.datasource.RetrofitImplementation
import com.example.mydictionary.model.datasource.RoomDataBaseImplementation
import com.example.mydictionary.model.repositiry.Repository
import com.example.mydictionary.model.repositiry.RepositoryImplementation
import com.example.mydictionary.model.repositiry.RepositoryImplementationLocal
import com.example.mydictionary.model.repositiry.RepositoryLocal
import com.example.mydictionary.room.HistoryDataBase
import com.example.mydictionary.view.history.HistoryInteractor
import com.example.mydictionary.view.main.MainInteractor
import com.example.mydictionary.viewmodel.HistoryViewModel
import com.example.mydictionary.viewmodel.MainViewModel
import org.koin.dsl.module

/**Для удобства создадим две переменные: в одной находятся зависимости, используемые во всём приложении, во второй - зависимости конкретного экрана.
 *
 * Функция single сообщает Koin, что эта зависимость должна храниться в виде синглтона (в Dagger есть похожая аннотация) Аннотация named выполняет аналогичную Dagger функцию*/

val application = module {
    /** single указывает, что БД должна быть в единственном экземпляре */
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    /** Получаем DAO */
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

/**Функция factory сообщает Koin, что эту зависимость нужно создавать каждый раз заново, что как раз подходит для Activity и её компонентов.*/
val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}