package com.example.noteapp.repository

import android.app.Application
import com.example.noteapp.data.Note
import com.example.noteapp.db.AppDatabase
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NoteRepository(application: Application) {

    val noteDBRequest = AppDatabase.getInstance(application).noteDAO()

    fun insertNote(note: Note): Completable {
        return noteDBRequest.insertAll(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllNote(): Single<List<Note>> {
        return noteDBRequest.getAllNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateNote(note:Note):Completable{
        return noteDBRequest.update(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}