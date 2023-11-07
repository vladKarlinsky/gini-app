package com.example.gini.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gini.data.dao.GiniDao
import com.example.gini.data.model.ImageEntity

@Database(entities = [ImageEntity::class], version = 1, exportSchema = false)
abstract class GiniDatabase: RoomDatabase() {
    abstract fun giniDao(): GiniDao
}