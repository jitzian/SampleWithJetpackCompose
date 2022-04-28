package com.example.mygithubreposwithcompose.ui.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mygithubreposwithcompose.R

@Composable
fun ArrowBackIcon(
    showBackButton: Boolean = false,
    onUpClick: (() -> Unit)? = null
) {
    if (showBackButton) {
        onUpClick?.let {
            IconButton(onClick = it) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.go_to_previous_screen_text)
                )
            }
        }
    }
}