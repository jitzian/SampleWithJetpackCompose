package com.example.mygithubreposwithcompose.rest

import com.example.mygithubreposwithcompose.rest.model.ResultApi
import com.example.mygithubreposwithcompose.rest.model.ResultApiItem
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {
    @GET("users/{user}/repos")
    suspend fun getRepos(@Path("user") user: String): ResultApi

    @GET("repos/{user}/{id}")
    suspend fun getRepoDetailsById(
        @Path("user") user: String,
        @Path("id") id: String
    ): ResultApiItem?
}