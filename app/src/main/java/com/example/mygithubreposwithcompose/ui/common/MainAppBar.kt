package com.example.mygithubreposwithcompose.ui.common

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import com.example.mygithubreposwithcompose.R

@ExperimentalMaterialApi
@Composable
fun MainAppBar(
    title: String = stringResource(id = R.string.title_app_name),
    showBackButton: Boolean = false,
    showMore: Boolean = false,
    menuOptions: List<String>? = null,
    onUpClick: (() -> Unit)? = null
) {

    val showMenu by rememberSaveable {
        mutableStateOf(showMore)
    }

    if (showBackButton) {
        TopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = {
                ArrowBackIcon(showBackButton, onUpClick)
            },
            actions = {
                if (!menuOptions.isNullOrEmpty()) {
                    if (showMore) {
                        CustomDropDownMenuState(
                            menuOptions = menuOptions,
                            showMore = showMenu
                        )
                    }
                }
            }
        )
    } else {
        TopAppBar(title = { Text(text = title) })
    }
}