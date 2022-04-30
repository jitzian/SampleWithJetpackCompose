package com.example.mygithubreposwithcompose.ui.screens.detail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mygithubreposwithcompose.R
import com.example.mygithubreposwithcompose.ui.common.MainAppBar

@ExperimentalMaterialApi
@Composable
fun CharacterDetailScaffold(
    menuOptions: List<String>? = null,
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            MainAppBar(
                showBackButton = true,
                onUpClick = onUpClick,
                showMore = true,
                menuOptions = menuOptions
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = stringResource(id = R.string.share_repo_text)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = content
    )
}
