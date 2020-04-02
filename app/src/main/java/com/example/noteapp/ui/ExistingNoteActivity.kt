package com.example.noteapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteapp.R
import kotlinx.android.synthetic.main.activity_existing_note.*

class ExistingNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_existing_note)
        val noteTitle = intent.getStringExtra("noteTitle")
        val noteBody = intent.getStringExtra("noteBody")
        et_nameOfExistingNote.setText(noteTitle)
        et_bodyOfExistingNote.setText(noteBody)
    }
}
