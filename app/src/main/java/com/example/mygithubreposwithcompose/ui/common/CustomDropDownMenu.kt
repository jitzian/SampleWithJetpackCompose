package com.example.mygithubreposwithcompose.ui.common

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import com.example.mygithubreposwithcompose.R

@ExperimentalMaterialApi
@Composable
fun CustomDropDownMenuState(menuOptions: List<String> = emptyList(), showMore: Boolean = false) {
    var showMoreState by rememberSaveable {
        mutableStateOf(showMore)
    }

    val menuOptionsState by rememberSaveable {
        mutableStateOf(menuOptions)
    }

    CustomDropDownMenu(
        showMore = showMoreState,
        menuOptions = menuOptionsState,
        onOptionClick = {
            showMoreState = !showMoreState
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun CustomDropDownMenu(
    showMore: Boolean = false,
    menuOptions: List<String>,
    onOptionClick: () -> Unit
) {
    IconButton(onClick = {
        onOptionClick()
    }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(
                id = R.string.more_options_text
            )
        )
        DropdownMenu(expanded = showMore, onDismissRequest = {
            onOptionClick()
        }) {
            menuOptions.forEach { option ->
                DropdownMenuItem(onClick = {
                    onOptionClick()
                }) {
                    ListItem(text = { Text(text = option) })
                }
            }
        }
    }

}