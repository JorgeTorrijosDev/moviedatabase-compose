package com.example.moviedatabase.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviesfav")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo("poster")
    val poster: String?,
)
