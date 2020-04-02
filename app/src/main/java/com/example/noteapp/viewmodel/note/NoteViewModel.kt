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

    fun getNotes() {
        disposable.add(
            repository.getAllNote()
                .subscribe({
                    Log.d("dbget",it.toString())
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