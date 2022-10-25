package com.example.mydictionary.di.components

import android.content.Context
import com.example.mydictionary.di.modules.ActivityModule
import com.example.mydictionary.di.modules.InteractorModule
import com.example.mydictionary.di.modules.RepositoryModule
import com.example.mydictionary.di.modules.ViewModelModule
import com.example.mydictionary.view.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**Тут мы прописываем все наши модули*/
@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
}