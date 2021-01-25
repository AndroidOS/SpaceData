package com.manuelcarvalho.nasaapi.model
//
//import androidx.room.Dao
//import androidx.room.Insert


//@Dao
//interface PhotoDao {
//
//    @Insert
//    suspend fun insertAll(vararg photos: Photo1): List<Photo1>
//
//    @androidx.room.Query("SELECT * FROM photos")
//    suspend fun getAllPhotos(): List<Photo1>
//
//    @androidx.room.Query("SELECT * FROM photos WHERE uuid = :photoId")
//    suspend fun getPhoto(quakeId: Int): Photo1
//
//    @androidx.room.Query("DELETE FROM photos")
//    suspend fun deleteAllPhotos()
//}