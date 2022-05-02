package com.example.mygithubreposwithcompose.ui.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mygithubreposwithcompose.R
import com.example.mygithubreposwithcompose.constatns.GlobalConstants.Companion.EMPTY_STRING
import com.example.mygithubreposwithcompose.rest.model.ResultApiItem
import com.example.mygithubreposwithcompose.ui.app.ReposApp
import com.example.mygithubreposwithcompose.ui.screens.error.ConnectivityError

@ExperimentalMaterialApi
@Composable
fun DetailScreenState(
    detailViewModel: DetailViewModel = viewModel(),
    user: String,
    repoName: String,
    onUpClick: () -> Unit
) {

    val data by detailViewModel.data.collectAsState()
    detailViewModel.getRepoDetailsById(user = user, repoName = repoName)

    when (data) {
        is DetailViewModel.UIState.Success -> {
            DetailScreen(
                repo = (data as DetailViewModel.UIState.Success).repo,
                menuOptions = (data as DetailViewModel.UIState.Success).menuOptions,
                onUpClick = onUpClick
            )
        }
        is DetailViewModel.UIState.Error -> {
            ConnectivityError(message = stringResource(id = R.string.no_data_retrieved_text))
        }
        is DetailViewModel.UIState.Empty -> Unit
    }
}

@ExperimentalMaterialApi
@Composable
fun DetailScreen(
    repo: ResultApiItem,
    menuOptions: List<String>? = null,
    onUpClick: () -> Unit
) {
    ReposApp {
        CharacterDetailScaffold(
            menuOptions = menuOptions,
            repo = repo,
            onUpClick = onUpClick
        ) {
            Box(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.dimen_16_dp))
                    .clickable(onClick = onUpClick)
            ) {
                Column {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = repo.name ?: EMPTY_STRING, style = MaterialTheme.typography.subtitle1
                    )

                    Text(
                        text = repo.description ?: stringResource(id = R.string.loremIpsum),
                        modifier = Modifier.padding(
                            top = 8.dp
                        )
                    )
                }
            }
        }
    }

}