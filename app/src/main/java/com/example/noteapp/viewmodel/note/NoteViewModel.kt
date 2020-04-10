package com.example.noteapp.viewmodel.note

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapp.data.Note
import com.example.noteapp.repository.NoteRepository
import io.reactivex.disposables.CompositeDisposable

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val notelivedata = MutableLiveData<List<Note>>()
    val error = MutableLiveData<String>()
    val disposable = CompositeDisposable()
    val dbState = MutableLiveData<DBState>()

    fun getNotes() {
        disposable.add(
            repository.getAllNote()
                .subscribe({
                    notelivedata.value = it
                    Log.d("dbget", it.toString())
                }, {
                    error.value = it.localizedMessage
                })
        )
    }

    fun deleteNote(note: Note) {
        disposable.add(
            repository.deleteNote(note)
                .subscribe({
                    dbState.value = DBState.SUCCESS
                }, {
                    dbState.value = DBState.FAILURE
                })
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    sealed class DBState {
        object SUCCESS : DBState()
        object FAILURE : DBState()
    }

}