package com.example.noteapp.viewmodel.existingnote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapp.data.Note
import com.example.noteapp.repository.NoteRepository
import io.reactivex.disposables.CompositeDisposable

class ExistingNoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val error = MutableLiveData<String>()
    val disposable = CompositeDisposable()
    val dbstate = MutableLiveData<DBState>()

    fun updateNote(note: Note) {
        disposable.add(
            repository.updateNote(note)
                .subscribe({
                    dbstate.value = DBState.Success
                }, {
                    dbstate.value = DBState.Failure
                    error.value = it.localizedMessage
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