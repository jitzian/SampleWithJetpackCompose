package com.example.mygithubreposwithcompose.ui.screens.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.example.mygithubreposwithcompose.rest.model.ResultApiItem
import com.example.mygithubreposwithcompose.ui.common.ItemRow

/**
 * Note for Reference: https://dev.to/paulallies/jetpack-compose-api-data-to-list-view-5fki
 * https://www.waseefakhtar.com/android/recyclerview-in-jetpack-compose/
 * */

@ExperimentalCoilApi
@Composable
fun MainScreenState(user: String, mainViewModel: MainViewModel = viewModel()) {

    val data by mainViewModel.data.collectAsState()
    mainViewModel.getRepos(user = user)

    if (!data.isError) {
        data.repos?.let { MainScreen(it) }
    }
}


@ExperimentalCoilApi
@Composable
fun MainScreen(repos: List<ResultApiItem>) {
    LazyColumn {
        items(repos) { item ->
            ItemRow(data = item)
        }
    }
}


