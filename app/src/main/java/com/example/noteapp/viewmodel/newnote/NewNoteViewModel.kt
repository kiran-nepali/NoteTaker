package com.example.noteapp.viewmodel.newnote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapp.data.Note
import com.example.noteapp.repository.NoteRepository
import io.reactivex.disposables.CompositeDisposable

class NewNoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val error = MutableLiveData<String>()
    val disposable = CompositeDisposable()
    val dbState = MutableLiveData<DBState>()

    fun insertNote(note: Note) {
        disposable.add(
            repository.insertNote(note)
                .subscribe({
                    Log.d("dbinsertion", note.title)
                    dbState.value = DBState.Success
                }, {
                    error.value = it.localizedMessage
                    dbState.value = DBState.Failure
                })
        )
    }

    sealed class DBState {
        object Success : DBState()
        object Failure : DBState()
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}