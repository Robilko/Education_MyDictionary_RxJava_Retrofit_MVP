package com.example.mydictionary.di.modules

import com.example.mydictionary.di.NAME_LOCAL
import com.example.mydictionary.di.NAME_REMOTE
import com.example.mydictionary.model.data.DataModel
import com.example.mydictionary.model.datasource.DataSource
import com.example.mydictionary.model.datasource.RetrofitImplementation
import com.example.mydictionary.model.datasource.RoomDataBaseImplementation
import com.example.mydictionary.model.repositiry.Repository
import com.example.mydictionary.model.repositiry.RepositoryImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>): Repository<List<DataModel>> = RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal  fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>): Repository<List<DataModel>> = RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal  fun provideDataSourceRemote(): DataSource<List<DataModel>> = RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal  fun provideDataSourceLocal(): DataSource<List<DataModel>> = RoomDataBaseImplementation()
}