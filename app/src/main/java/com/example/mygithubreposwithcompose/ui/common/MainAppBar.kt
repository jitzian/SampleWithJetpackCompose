package com.example.mygithubreposwithcompose.ui.common

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.mygithubreposwithcompose.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainAppBar(
    title: String = stringResource(id = R.string.title_app_name),
    showBackButton: Boolean = false,
    showMore: Boolean = false,
    onUpClick: (() -> Unit)? = null
) {

    val listOfMenu = listOf("Option 1", "Option 2", "Option 3")
    var showMenu by rememberSaveable {
        mutableStateOf(false)
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
                if (showMore) {
                    IconButton(onClick = {
                        showMenu = !showMenu
                    }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(
                                id = R.string.more_options_text
                            )
                        )
                        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                            listOfMenu.forEach { option ->
                                DropdownMenuItem(onClick = { showMenu = false }) {
                                    ListItem(text = { Text(text = option) })
                                }
                            }

                        }
                    }
                }
            }
        )
    } else {
        TopAppBar(title = { Text(text = title) })
    }
}