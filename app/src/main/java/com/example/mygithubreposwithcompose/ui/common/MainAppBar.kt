package com.example.mygithubreposwithcompose.ui.common

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
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
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(
                                id = R.string.more_options_text
                            )
                        )
                        DropdownMenu(expanded = true, onDismissRequest = { /*TODO*/ }) {
                            listOfMenu.forEach { option ->
                                DropdownMenuItem(onClick = { /*TODO*/ }) {
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