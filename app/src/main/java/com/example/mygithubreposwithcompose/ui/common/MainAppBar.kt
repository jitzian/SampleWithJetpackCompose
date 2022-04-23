package com.example.mygithubreposwithcompose.ui.common


import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mygithubreposwithcompose.R

@Composable
fun MainAppBar(title: String = stringResource(id = R.string.title_app_name)) {
    TopAppBar(
        title = {
            Text(text = title)
        }
    )
}