package com.example.mygithubreposwithcompose.rest

import com.example.mygithubreposwithcompose.rest.model.ResultApi
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {
    @GET("users/{user}/repos")
    suspend fun getRepos(@Path("user") user: String): ResultApi
}