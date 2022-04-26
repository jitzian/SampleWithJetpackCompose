package com.example.mygithubreposwithcompose.dagger.components

import com.example.mygithubreposwithcompose.base.BaseViewModel
import com.example.mygithubreposwithcompose.dagger.modules.NetworkModule
import dagger.Component

@Component(
    modules = [NetworkModule::class]
)
interface ComponentInjector {

    fun inject(baseViewModel: BaseViewModel)

    @Component.Builder
    interface Builder {
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): ComponentInjector
    }

}