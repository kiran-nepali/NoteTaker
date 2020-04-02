package com.example.noteapp.viewmodel.existingnote

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.repository.NoteRepository

class ExistingNoteViewModelFactory(private val repository: NoteRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExistingNoteViewModel(repository) as T
    }
}