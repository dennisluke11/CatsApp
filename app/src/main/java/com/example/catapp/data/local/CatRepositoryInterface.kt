package com.example.catapp.data.local

interface CatRepositoryInterface {
    suspend fun refreshCats()
    suspend fun getLocalCats(): List<CatEntity>
}