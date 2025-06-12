package com.example.catapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CatDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(cats: List<CatEntity>)

    @Query("SELECT * FROM cats")
    suspend fun getAllCats(): List<CatEntity>
}