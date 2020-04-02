package com.example.noteapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.R
import com.example.noteapp.data.Note
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.viewmodel.newnote.NewNoteViewModel
import com.example.noteapp.viewmodel.newnote.NewNoteViewModelFactory
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        val repository = NoteRepository(application)
        val factory =
            NewNoteViewModelFactory(
                repository
            )
//        val note = Note(0, title = "hello", body = "hello from noteapp")

        val viewModel = ViewModelProvider(this, factory).get(NewNoteViewModel::class.java)
//        viewModel.insertNote(note)

        btn_save.setOnClickListener {
            val notetitle = et_nameOfNewNote.text.toString()
            val bodytitle = et_bodyOfNote.text.toString()
            if (notetitle.isEmpty()) {
                et_nameOfNewNote.error = "title should not be empty"
            } else if (bodytitle.isEmpty()) {
                et_bodyOfNote.error = "message should not be empty"
            } else {
                val insertnote = Note(notetitle, bodytitle)
                viewModel.insertNote(insertnote)
            }
        }

        val errormsg = viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            Log.d("errornote", it)
        })

        fun successmsg() {
            Toast.makeText(this, "Successfuly inserted", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@NewNoteActivity, MainActivity::class.java)
            startActivity(intent)
        }
        viewModel.dbState.observe(this, Observer {
            when (it) {
                is NewNoteViewModel.DBState.Success -> successmsg()
                is NewNoteViewModel.DBState.Failure -> errormsg
            }
        })
    }
}
