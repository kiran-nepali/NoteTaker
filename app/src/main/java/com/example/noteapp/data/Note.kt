package com.example.noteapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note (
    @PrimaryKey val id:Int,
    @ColumnInfo(name = "title") val title:String?,
    @ColumnInfo(name = "body") val body:String?
)

