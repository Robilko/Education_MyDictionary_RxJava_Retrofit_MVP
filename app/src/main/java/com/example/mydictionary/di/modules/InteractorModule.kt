package com.example.mydictionary.di.modules

import com.example.mydictionary.di.NAME_LOCAL
import com.example.mydictionary.di.NAME_REMOTE
import com.example.mydictionary.model.data.DataModel
import com.example.mydictionary.model.repositiry.Repository
import com.example.mydictionary.view.main.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}