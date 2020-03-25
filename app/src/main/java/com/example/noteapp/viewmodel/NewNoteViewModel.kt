package com.example.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapp.data.Note
import com.example.noteapp.db.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewNoteViewModel(application: Application) : ViewModel() {

    val notelivedata = MutableLiveData<List<Note>>()
    val error = MutableLiveData<String>()
    val disposable = CompositeDisposable()
    val noteDBRequest = AppDatabase.getInstance(application).noteDAO()
    val dbState = MutableLiveData<DBState>()

    fun insertNote(note: Note) {
        disposable.add(
            noteDBRequest.insertAll(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    dbState.value = DBState.Success
                }, {
                    dbState.value = DBState.Failure
                })
        )
    }

    sealed class DBState {
        object Success : DBState()
        object Failure : DBState()
    }
}