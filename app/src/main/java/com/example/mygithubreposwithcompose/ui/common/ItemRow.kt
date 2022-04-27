package com.example.mygithubreposwithcompose.ui.common

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import coil.annotation.ExperimentalCoilApi
import com.example.mygithubreposwithcompose.R
import com.example.mygithubreposwithcompose.rest.model.ResultApiItem
import com.example.mygithubreposwithcompose.utils.safeLet
import java.util.*

@ExperimentalCoilApi
@Composable
fun ItemRow(data: ResultApiItem, onRepoClick: (String, String) -> Unit) {
    val TAG = "ItemRow"
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.dimen_8_dp))
            .clickable {
                Log.e("ItemRow", "ItemRow: ")
                safeLet(data.owner, data.owner?.login, data.name) { _, user, repoName ->
                    Log.e(TAG, "ItemRow: user: $user, repoId: $repoName")
                    onRepoClick(user, repoName)
                }
            },
        elevation = dimensionResource(id = R.dimen.dimen_4_dp)
    ) {
        Row {
            Thumb(data = data)
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${data.name?.uppercase(Locale.ROOT)}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.dimen_8_dp)),
                    style = MaterialTheme.typography.subtitle2,
                )
                Text(
                    text = "${data.description}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.dimen_8_dp)),
                    maxLines = 2,
                )
            }
        }
    }
}