package com.example.mygithubreposwithcompose.dagger.components

import com.example.mygithubreposwithcompose.dagger.modules.NetworkModule
import com.example.mygithubreposwithcompose.ui.screens.main.MainViewModel
import dagger.Component

@Component(
    modules = [NetworkModule::class]
)
interface ComponentInjector {

    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): ComponentInjector
    }

}