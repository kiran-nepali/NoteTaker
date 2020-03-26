package com.example.noteapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapp.data.Note
import com.example.noteapp.repository.NoteRepository
import io.reactivex.disposables.CompositeDisposable

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val notelivedata = MutableLiveData<List<Note>>()
    val error = MutableLiveData<String>()
    val disposable = CompositeDisposable()

    fun getNotes() {
        disposable.add(
            repository.getAllNote()
                .subscribe({
                    notelivedata.value = it
                }, {
                    error.value = it.localizedMessage
                })
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}