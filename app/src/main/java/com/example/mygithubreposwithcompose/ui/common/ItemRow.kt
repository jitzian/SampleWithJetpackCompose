package com.example.mygithubreposwithcompose.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.mygithubreposwithcompose.rest.model.ResultApiItem

@ExperimentalCoilApi
@Composable
fun ItemRow(data: ResultApiItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Row {
            Thumb(data = data)
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${data.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Text(
                    text = "${data.description}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    maxLines = 2,

                    )
            }
        }
    }
}