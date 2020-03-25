package com.example.noteapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note (
    @PrimaryKey var id:Int,
    @ColumnInfo(name = "title") var title:String?,
    @ColumnInfo(name = "body") var body:String?
)

