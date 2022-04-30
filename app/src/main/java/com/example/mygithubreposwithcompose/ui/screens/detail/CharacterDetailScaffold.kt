package com.example.mygithubreposwithcompose.ui.screens.detail

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import com.example.mygithubreposwithcompose.R
import com.example.mygithubreposwithcompose.rest.model.ResultApiItem
import com.example.mygithubreposwithcompose.ui.common.MainAppBar

@ExperimentalMaterialApi
@Composable
fun CharacterDetailScaffold(
    menuOptions: List<String>? = null,
    repo: ResultApiItem,
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current
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
            FloatingActionButton(onClick = {
                shareRepository(
                    context = context,
                    repo = repo
                )
            }) {
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

fun shareRepository(context: Context, repo: ResultApiItem) {
    ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setText(repo.htmlUrl)
        .intent
        .also(context::startActivity)
}