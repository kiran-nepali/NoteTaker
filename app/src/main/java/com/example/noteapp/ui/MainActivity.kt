package com.example.noteapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.RoomDatabase
import com.example.noteapp.R
import com.example.noteapp.data.Note
import com.example.noteapp.db.AppDatabase
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.viewmodel.NoteViewModel
import com.example.noteapp.viewmodel.NoteViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = NoteRepository(application)
        val factory = NoteViewModelFactory(repository)
        val viewModel = ViewModelProvider(this,factory).get(NoteViewModel::class.java)
        viewModel.getNotes()

        viewModel.notelivedata.observe(this, Observer {
            noteAdapter(it)
        })
        fab_btn_note.setOnClickListener {
            val intent = Intent(this@MainActivity,NewNoteActivity::class.java)
            startActivity(intent)
        }
    }

    fun noteAdapter(note:List<Note>){
        val adapter = NoteAdapter(note)
        rv_notelist.layoutManager = LinearLayoutManager(this)
        rv_notelist.adapter = adapter
    }
}
