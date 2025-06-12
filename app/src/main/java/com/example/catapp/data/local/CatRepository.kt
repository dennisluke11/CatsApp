package com.example.catapp.data.local

import com.example.catapp.data.remote.CatApiService


class CatRepository(
    private val catDao: CatDao,
    private val apiService: CatApiService
) {

    suspend fun refreshCats() {
        try {
            val response = apiService.getCatImages()

            val cats = response.mapIndexed { index, item ->
                val title = "Image ${index + 1}"
                CatEntity(
                    url = item.url,
                    title = title,
                    description = "This is the description for $title. It’s a really cool image, bask in its gloriousness."
                )
            }

            catDao.insertAll(cats)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getLocalCats(): List<CatEntity> = catDao.getAllCats()
}
