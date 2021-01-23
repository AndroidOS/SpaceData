package com.manuelcarvalho.nasaapi.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Photo1::class), version = 1)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun quakeDao(): Photo1

    companion object {
        @Volatile
        private var instance: PhotoDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PhotoDatabase::class.java,
            "photos"
        ).build()
    }
}