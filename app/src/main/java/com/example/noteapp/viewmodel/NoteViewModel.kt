package com.example.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapp.data.Note
import com.example.noteapp.db.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NoteViewModel(application: Application):ViewModel() {

    val notelivedata = MutableLiveData<List<Note>>()
    val error = MutableLiveData<String>()
    val disposable = CompositeDisposable()
    val noteDBRequest = AppDatabase.getInstance(application).noteDAO()

    fun getNotes(){
        disposable.add(
        noteDBRequest.getAllNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                notelivedata.value = it
            },{
                error.value = it.localizedMessage
            })
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}