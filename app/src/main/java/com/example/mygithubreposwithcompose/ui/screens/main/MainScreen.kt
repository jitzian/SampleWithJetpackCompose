package com.example.mygithubreposwithcompose.ui.screens.main

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.example.mygithubreposwithcompose.rest.model.ResultApiItem
import com.example.mygithubreposwithcompose.ui.app.ReposApp
import com.example.mygithubreposwithcompose.ui.common.ItemRow
import com.example.mygithubreposwithcompose.ui.common.MainAppBar
import com.example.mygithubreposwithcompose.ui.screens.error.ConnectivityError

/**
 * Note for Reference: https://dev.to/paulallies/jetpack-compose-api-data-to-list-view-5fki
 * https://www.waseefakhtar.com/android/recyclerview-in-jetpack-compose/
 * */

@ExperimentalCoilApi
@Composable
fun MainScreenState(
    mainViewModel: MainViewModel = viewModel(),
    onRepoClick: (String, String) -> Unit
) {
    val TAG = "MainScreenState"

    val data by mainViewModel.data.collectAsState()
    mainViewModel.getRepos(user = "jitzian")

    when (data) {
        is MainViewModel.UIState.SuccessState -> {
            Log.e(TAG, "MainScreenState::SuccessState")
            MainScreen(
                repos = (data as MainViewModel.UIState.SuccessState).repos,
                onRepoClick
            )
        }

        is MainViewModel.UIState.ErrorState -> {
            Log.e(TAG, "MainScreenState::ErrorState")
            ConnectivityError((data as MainViewModel.UIState.ErrorState).message)
        }

        is MainViewModel.UIState.EmptyState -> Unit
    }

}

@ExperimentalCoilApi
@Composable
fun MainScreen(repos: List<ResultApiItem>, onRepoClick: (String, String) -> Unit) {
    ReposApp {
        Scaffold(
            topBar = {
                MainAppBar()
            }
        ) {
            LazyColumn {
                items(repos) { item ->
                    ItemRow(
                        data = item,
                        onRepoClick = onRepoClick
                    )
                }
            }
        }
    }
}


