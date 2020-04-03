package com.example.noteapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.data.Note
import kotlinx.android.synthetic.main.list_view_holder.view.*

class NoteAdapter(private val note:List<Note>,private val listener: OnNoteClickListener):RecyclerView.Adapter<NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_view_holder,parent,false))
    }

    override fun getItemCount(): Int {
        return note.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.title.text = note[position].title
        holder.bind(note[position],listener)
        holder.delbtn.setOnClickListener {
            listener.onClickDeleteBtn(note[position])
        }
    }
}

class NoteViewHolder(view: View):RecyclerView.ViewHolder(view){
    val title = view.tv_nameOfNote_viewholder
    val delbtn = view.btn_delete
    fun bind(note: Note,listener: OnNoteClickListener){
        itemView.setOnClickListener {
            listener.onClicked(note)
        }
    }
}

interface OnNoteClickListener{
    fun onClicked(note:Note)
    fun onClickDeleteBtn(note:Note)
}