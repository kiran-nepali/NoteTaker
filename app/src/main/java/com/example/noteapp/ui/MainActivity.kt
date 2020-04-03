package com.example.noteapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.data.Note
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.viewmodel.note.NoteViewModel
import com.example.noteapp.viewmodel.note.NoteViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_view_holder.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = NoteRepository(application)
        val factory =
            NoteViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)
        viewModel.getNotes()

        viewModel.notelivedata.observe(this, Observer {
            noteAdapter(it)
        })
        fab_btn_note.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivity(intent)
        }
    }

    fun noteAdapter(note: List<Note>) {
        val adapter = NoteAdapter(note, object : OnNoteClickListener {
            override fun onClicked(note: Note) {
                val intent = Intent(this@MainActivity, ExistingNoteActivity::class.java)
                intent.putExtra("noteTitle", note.title)
                intent.putExtra("noteBody", note.body)
                intent.putExtra("noteId", note.id)
                startActivity(intent)
            }

            override fun onClickDeleteBtn(note:Note) {
                val noteid = note.id
                Toast.makeText(this@MainActivity,noteid.toString(),Toast.LENGTH_SHORT).show()
            }

        })
        rv_notelist.layoutManager = LinearLayoutManager(this)
        rv_notelist.adapter = adapter

    }
}
