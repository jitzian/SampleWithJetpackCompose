package com.example.mygithubreposwithcompose.base

import androidx.lifecycle.ViewModel
import com.example.mygithubreposwithcompose.constatns.GlobalConstants
import com.example.mygithubreposwithcompose.dagger.components.DaggerComponentInjector
import com.example.mygithubreposwithcompose.dagger.modules.NetworkModule
import com.example.mygithubreposwithcompose.rest.RestApi
import retrofit2.Retrofit
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    protected var TAG: String? = null

    private val injector = DaggerComponentInjector.builder()
        .networkModule(NetworkModule(GlobalConstants.baseUrl))
        .build()

    @Inject
    protected lateinit var retrofit: Retrofit

    protected var restApi: RestApi

    init {
        inject()
        restApi = retrofit.create(RestApi::class.java)
    }

    private fun inject() {
        injector.inject(this)
    }


}