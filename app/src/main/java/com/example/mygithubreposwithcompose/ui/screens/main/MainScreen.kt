package com.example.mygithubreposwithcompose.ui.screens.main

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
    onRepoClick: (Int) -> Unit
) {

    val data by mainViewModel.data.collectAsState()
    mainViewModel.getRepos("jitzian")

    if (data.isError) {
        ConnectivityError()
    } else {
        data.repos?.let {
            MainScreen(
                repos = it,
                onRepoClick = onRepoClick
            )
        }
    }
}


@ExperimentalCoilApi
@Composable
fun MainScreen(repos: List<ResultApiItem>, onRepoClick: (Int) -> Unit) {
    ReposApp {
        Scaffold(
            topBar = {
                MainAppBar()
            }
        ) {
            LazyColumn {
                items(repos) { item ->
                    item.id?.let { safeRepoId ->
                        ItemRow(
                            data = item,
                            onRepoClick = {
                                onRepoClick(safeRepoId)
                            }
                        )
                    }
                }
            }
        }
    }
}


