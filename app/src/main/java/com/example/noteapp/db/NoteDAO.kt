package com.example.noteapp.db

import androidx.room.*
import com.example.noteapp.data.Note
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface NoteDAO {

    @Query("SELECT * FROM note")
    fun getAllNotes():Single<List<Note>>

    @Query("SELECT * FROM note WHERE id IN (:noteID)")
    fun getAllNotesById(noteID:IntArray):List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(note:Note):Completable

    @Delete
    fun delete(note:Note):Completable

    @Update
    fun update(note: Note):Completable
}