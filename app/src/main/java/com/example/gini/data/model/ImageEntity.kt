package com.example.gini.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Images")
data class ImageEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "uri") val uri: String?,
    @ColumnInfo(name = "likes") val likes: String?,
    @ColumnInfo(name = "comments") val comments: String?

)
