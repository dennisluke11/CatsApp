package com.example.catapp.data.remote

import com.google.gson.annotations.SerializedName


data class CatApiItem(
    val id: String,
    val url: String,
    @SerializedName("source_url") val sourceUrl: String
)

