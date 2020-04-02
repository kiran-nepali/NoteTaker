package com.example.noteapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.R
import com.example.noteapp.data.Note
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.viewmodel.existingnote.ExistingNoteViewModel
import com.example.noteapp.viewmodel.existingnote.ExistingNoteViewModelFactory
import kotlinx.android.synthetic.main.activity_existing_note.*

class ExistingNoteActivity : AppCompatActivity() {

    private lateinit var viewModel: ExistingNoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_existing_note)
        val noteTitle = intent.getStringExtra("noteTitle")
        val noteBody = intent.getStringExtra("noteBody")
        val noteId = intent.getLongExtra("noteId", 0)
        et_nameOfExistingNote.setText(noteTitle)
        et_bodyOfExistingNote.setText(noteBody)
        val repository = NoteRepository(application)
        val factory = ExistingNoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ExistingNoteViewModel::class.java)
        btn_update.setOnClickListener {
            val notetitle = et_nameOfExistingNote.text.toString()
            val notebody = et_bodyOfExistingNote.text.toString()
            val updatednote = Note(notetitle, notebody)
            updatednote.id = noteId
            if (noteId == updatednote.id) {
                viewModel.updateNote(updatednote)
            }
        }

        fun successmsg() {
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        fun errormsg() {
            viewModel.error.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
        }
        viewModel.dbstate.observe(this, Observer {
            when (it) {
                is ExistingNoteViewModel.DBState.Success -> successmsg()
                is ExistingNoteViewModel.DBState.Failure -> errormsg()
            }
        })
    }
}
