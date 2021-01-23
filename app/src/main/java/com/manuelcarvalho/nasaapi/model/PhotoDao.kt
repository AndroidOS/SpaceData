package com.manuelcarvalho.nasaapi.model

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface PhotoDao {

    @Insert
    suspend fun insertAll(vararg photos: Photo): List<Photo>

    @androidx.room.Query("SELECT * FROM photos")
    suspend fun getAllQuakes(): List<Photo>

    @androidx.room.Query("SELECT * FROM photos WHERE uuid = :quakeId")
    suspend fun getQuake(quakeId: Int): Photo

    @androidx.room.Query("DELETE FROM photos")
    suspend fun deleteAllQuakes()
}