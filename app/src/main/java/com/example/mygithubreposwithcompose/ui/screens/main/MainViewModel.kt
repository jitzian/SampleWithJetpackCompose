package com.example.mygithubreposwithcompose.ui.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygithubreposwithcompose.constatns.GlobalConstants
import com.example.mygithubreposwithcompose.dagger.components.DaggerComponentInjector
import com.example.mygithubreposwithcompose.dagger.modules.NetworkModule
import com.example.mygithubreposwithcompose.rest.RestApi
import com.example.mygithubreposwithcompose.rest.model.ResultApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import javax.inject.Inject

class MainViewModel : ViewModel() {
    private val TAG = MainViewModel::class.java.simpleName

    private val injector = DaggerComponentInjector.builder()
        .networkModule(NetworkModule(baseURL = GlobalConstants.baseUrl))
        .build()

    private var restApi: RestApi

    @Inject
    lateinit var retrofit: Retrofit

    init {
        inject()
        restApi = retrofit.create(RestApi::class.java)
    }

    private fun inject() {
        injector.inject(this)
    }

    fun getRepos(user: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            withTimeout(GlobalConstants.MAX_TIME_OUT) {
                try {
                    Log.e(TAG, "getRepos: ${restApi.getRepos(user).size}")

                } catch (tce: TimeoutCancellationException) {
                    Log.e(TAG, "getRepos: ${tce.message}")
                    ReposState.ReposError(tce.message)
                }
            }
        }
    }

    sealed class ReposState {
        class ReposSuccess(val data: ResultApi) : ReposState()
        class ReposError(val message: String?) : ReposState()
    }

}