package com.example.noteapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.data.Note

@Database(entities = arrayOf(Note::class),version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun noteDAO():NoteDAO

    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context:Context):AppDatabase {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "notedb")
                    .build()
            }
            return INSTANCE as AppDatabase
        }
    }
}