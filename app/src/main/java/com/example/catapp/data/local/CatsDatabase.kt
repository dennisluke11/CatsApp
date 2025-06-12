package com.example.catapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CatEntity::class], version = 1)
abstract class CatsDatabase : RoomDatabase() {

    abstract fun catDao(): CatDao

    companion object {
        @Volatile
        private var INSTANCE: CatsDatabase? = null

        fun getDatabase(context: Context): CatsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatsDatabase::class.java,
                    "cat_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}