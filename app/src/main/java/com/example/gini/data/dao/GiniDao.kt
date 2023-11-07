package com.example.gini.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gini.data.model.ImageEntity

@Dao
interface GiniDao {
    @Query("SELECT * FROM images")
    fun getAllImages(): LiveData<List<ImageEntity>>

    @Query("DELETE FROM images")
    fun deleteAllImages()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(image: ImageEntity): Long
}