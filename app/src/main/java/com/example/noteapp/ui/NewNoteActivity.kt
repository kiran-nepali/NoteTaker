package com.example.noteapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.RoomDatabase
import com.example.noteapp.R
import com.example.noteapp.data.Note
import com.example.noteapp.db.AppDatabase
import com.example.noteapp.viewmodel.NewNoteViewModel
import com.example.noteapp.viewmodel.NewNoteViewModelFactory
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNoteActivity : AppCompatActivity() {

    private lateinit var roomdb:RoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        roomdb = AppDatabase.getInstance(applicationContext)
        val factory = NewNoteViewModelFactory(application)
        val note = Note(0, title = "hello", body = "hello from noteapp")

        val viewModel = ViewModelProvider(this,factory).get(NewNoteViewModel::class.java)
        viewModel.insertNote(note)

//        if (et_nameOfNewNote.text.isEmpty()){
//            et_nameOfNewNote.error = "should not be empty"
//        }
//        if (et_bodyOfNote.text.isEmpty()){
//            et_bodyOfNote.error = "should not be empty"
//        }

//        val insertnote = Note(title = et_nameOfNewNote.text.toString(),body = et_bodyOfNote.text.toString())
        btn_save.setOnClickListener {
            viewModel.insertNote(note)
        }
        viewModel.dbState.observe(this, Observer{
            when (it){
                is NewNoteViewModel.DBState.Success -> Toast.makeText(this,"Successfuly inserted",Toast.LENGTH_SHORT).show()
                is NewNoteViewModel.DBState.Failure -> Toast.makeText(this,"Cannot insert",Toast.LENGTH_SHORT).show()
            }
        })

    }
}
