package com.example.mygithubreposwithcompose.ui.screens.main

import android.util.Log
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

    private val _data = MutableStateFlow<UIState>(UIState.EmptyState)
    var data = _data.asStateFlow()

    init {
        inject()
        restApi = retrofit.create(RestApi::class.java)
    }

    private fun inject() {
        injector.inject(this)
    }

    fun getRepos(user: String) = viewModelScope.launch {
        //TODO: Refactor this in order to handle proper errors like no connectivity..
        when (_data.value) {
            is UIState.EmptyState -> {
                withContext(Dispatchers.IO) {
                    withTimeout(GlobalConstants.MAX_TIME_OUT) {
                        try {
                            val result = restApi.getRepos(user)

                            if (result.isNotEmpty()) {
                                _data.value = UIState.SuccessState(repos = result)
                            } else {
                                _data.value = UIState.ErrorState(message = "No data retrieved")
                            }
                        } catch (tce: TimeoutCancellationException) {
                            Log.e(TAG, "getRepos::${tce.message}")
                            _data.value =
                                UIState.ErrorState(message = "An error occurred::${tce.message}")
                        }
                    }
                }
            }
            else -> Unit
        }
    }

    sealed class UIState {
        object EmptyState : UIState()
        class SuccessState(val repos: List<ResultApiItem>) : UIState()
        class ErrorState(val message: String) : UIState()
    }
}