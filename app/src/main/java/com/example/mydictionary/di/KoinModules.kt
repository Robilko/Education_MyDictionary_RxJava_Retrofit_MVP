package com.example.mydictionary.di

import androidx.room.Room
import com.example.historyscreen.HistoryActivity
import com.example.model.data.dto.SearchResultDto
import com.example.repository.datasource.RetrofitImplementation
import com.example.repository.datasource.RoomDataBaseImplementation
import com.example.repository.repositiry.Repository
import com.example.repository.repositiry.RepositoryImplementation
import com.example.repository.repositiry.RepositoryImplementationLocal
import com.example.repository.repositiry.RepositoryLocal
import com.example.repository.room.HistoryDataBase
import com.example.historyscreen.HistoryInteractor
import com.example.mydictionary.view.main.MainInteractor
import com.example.historyscreen.HistoryViewModel
import com.example.mydictionary.view.main.MainActivity
import com.example.mydictionary.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**Для удобства создадим две переменные: в одной находятся зависимости, используемые во всём приложении, во второй - зависимости конкретного экрана.
 *
 * Функция single сообщает Koin, что эта зависимость должна храниться в виде синглтона (в Dagger есть похожая аннотация) Аннотация named выполняет аналогичную Dagger функцию*/

val application = module {
    /** single указывает, что БД должна быть в единственном экземпляре */
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    /** Получаем DAO */
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<SearchResultDto>>> {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<RepositoryLocal<List<SearchResultDto>>> {
        RepositoryImplementationLocal(
            RoomDataBaseImplementation(
                get()
            )
        )
    }
}

/**Функция factory сообщает Koin, что эту зависимость нужно создавать каждый раз заново, что как раз подходит для Activity и её компонентов.*/
val mainScreen = module {
//    factory { MainViewModel(get()) }
//    factory { MainInteractor(get(), get()) }
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}

val historyScreen = module {
//    factory { HistoryViewModel(get()) }
//    factory { HistoryInteractor(get(), get()) }
    scope(named<HistoryActivity>()) {
        scoped { HistoryInteractor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }
}