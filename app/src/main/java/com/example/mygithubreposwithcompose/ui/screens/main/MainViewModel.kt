package com.example.mygithubreposwithcompose.ui.screens.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygithubreposwithcompose.constatns.GlobalConstants
import com.example.mygithubreposwithcompose.dagger.components.DaggerComponentInjector
import com.example.mygithubreposwithcompose.dagger.modules.NetworkModule
import com.example.mygithubreposwithcompose.rest.RestApi
import com.example.mygithubreposwithcompose.rest.model.ResultApiItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _data = MutableStateFlow(UIState())
    val data = _data.asStateFlow()

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
                    _data.value = UIState(
                        repos = restApi.getRepos(user)
                    )
                } catch (tce: TimeoutCancellationException) {
                    Log.e(TAG, "getRepos: ${tce.message}")
                }
            }
        }
    }

    //State that will be observed in the UI to display data
    data class UIState(
        val isError: Boolean = false,
        val repos: List<ResultApiItem>? = null
    )

}