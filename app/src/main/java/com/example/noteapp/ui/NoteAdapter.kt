package com.example.noteapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.data.Note
import kotlinx.android.synthetic.main.list_view_holder.view.*

class NoteAdapter(private val note:List<Note>):RecyclerView.Adapter<NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_view_holder,parent,false))
    }

    override fun getItemCount(): Int {
        return note.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.title.text = note[position].title
    }
}

class NoteViewHolder(view: View):RecyclerView.ViewHolder(view){
    val title = view.tv_nameOfNote_viewholder
}