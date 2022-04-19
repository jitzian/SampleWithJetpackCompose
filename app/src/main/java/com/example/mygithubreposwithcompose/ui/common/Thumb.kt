package com.example.mygithubreposwithcompose.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mygithubreposwithcompose.R
import com.example.mygithubreposwithcompose.rest.model.ResultApiItem

@Composable
@ExperimentalCoilApi
fun Thumb(
    data: ResultApiItem,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(dimensionResource(id = R.dimen.cell_thumb_height))
            .width(dimensionResource(id = R.dimen.cell_thumb_height)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberImagePainter(
                //TODO: Change
                //data = data.thumb,
                data = "https://cdn4.iconfinder.com/data/icons/bettericons/354/github-512.png"
            ),
            contentDescription = "${data.name}",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}