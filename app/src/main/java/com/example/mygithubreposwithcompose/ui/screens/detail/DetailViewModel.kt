package com.example.mygithubreposwithcompose.ui.screens.detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.mygithubreposwithcompose.base.BaseViewModel
import com.example.mygithubreposwithcompose.constatns.GlobalConstants
import com.example.mygithubreposwithcompose.rest.model.ResultApiItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel : BaseViewModel() {

    init {
        TAG = DetailViewModel::class.java.simpleName
    }

    private val _data = MutableStateFlow<UIState>(UIState.Empty)
    var data = _data.asStateFlow()

    private val menuOptions = mutableListOf(
        "Option 1",
        "Option 2",
        "Option 3"
    )

    fun getRepoDetailsById(user: String, repoName: String) = viewModelScope.launch {

        when (_data.value) {
            is UIState.Empty -> {
                withContext(Dispatchers.IO) {
                    withTimeout(GlobalConstants.MAX_TIME_OUT) {
                        try {
                            val repo = restApi.getRepoDetailsById(user, repoName)
                            if (repo == null) {
                                _data.value = UIState.Error("No data retrieved")
                            } else {
                                _data.value = UIState.Success(
                                    repo = repo,
                                    showMenu = true,
                                    menuOptions = menuOptions
                                )
                            }
                        } catch (tce: TimeoutCancellationException) {
                            Log.e(TAG, "getRepoDetailsById::${tce.message}")
                        }
                    }
                }
            }
            else -> Unit
        }
    }

    sealed class UIState {
        object Empty : UIState()
        class Success(
            val repo: ResultApiItem,
            val showMenu: Boolean = false,
            val menuOptions: List<String>? = null,
        ) : UIState()

        class Error(val msg: String) : UIState()
    }

}