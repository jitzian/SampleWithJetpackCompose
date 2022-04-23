package com.example.mygithubreposwithcompose.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mygithubreposwithcompose.R

@Composable
fun DetailScreen(onUpClick: () -> Unit) {

    Box(modifier = Modifier.padding(16.dp)) {
        Column {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Title", style = MaterialTheme.typography.subtitle1
            )

            Text(
                text = stringResource(id = R.string.loremIpsum),
                modifier = Modifier.padding(
                    top = 8.dp
                )
            )
        }
    }

}