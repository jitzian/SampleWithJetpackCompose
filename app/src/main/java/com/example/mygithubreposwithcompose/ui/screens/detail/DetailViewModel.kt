package com.example.mygithubreposwithcompose.ui.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygithubreposwithcompose.constatns.GlobalConstants
import kotlinx.coroutines.*

class DetailViewModel : ViewModel() {
    //TODO: Create a BaseViewModel for injecting the rest components once...
    private val TAG = DetailViewModel::class.java.simpleName

    fun getRepoDetailsById(id: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            withTimeout(GlobalConstants.MAX_TIME_OUT) {
                try {

                } catch (tce: TimeoutCancellationException) {
                    Log.e(TAG, "getRepoDetailsById::${tce.message}")
                }
            }
        }
    }

}