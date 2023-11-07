package com.example.gini.network

import com.example.gini.data.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api")
    suspend fun fetchData(@Query("key") key: String): ResponseModel
}