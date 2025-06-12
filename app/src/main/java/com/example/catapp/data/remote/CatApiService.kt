package com.example.catapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {

    @GET("api/images/get")
    suspend fun getCatImages(
        @Query("format") format: String = "json",
        @Query("results_per_page") resultsPerPage: Int = 100,
        @Query("size") size: String = "small",
        @Query("type") type: String = "png"
    ): List<CatApiItem>
}