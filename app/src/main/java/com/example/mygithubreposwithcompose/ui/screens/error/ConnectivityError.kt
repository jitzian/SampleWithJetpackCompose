package com.example.mygithubreposwithcompose.ui.screens.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mygithubreposwithcompose.R
import com.example.mygithubreposwithcompose.ui.app.ReposApp

@Composable
@Preview
fun ConnectivityError() {

    ReposApp {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.transpatent_owl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .padding(dimensionResource(id = R.dimen.dimen_16_dp))

            )
            Text(
                text = stringResource(id = R.string.no_internet_connection_text),
                modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_16_dp)),
                style = MaterialTheme.typography.h5
            )
        }
    }
}