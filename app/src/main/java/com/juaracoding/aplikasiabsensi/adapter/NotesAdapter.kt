package com.juaracoding.aplikasiabsensi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.juaracoding.aplikasiabsensi.R
import com.juaracoding.aplikasiabsensi.model.Notes

class NotesAdapter(val notesList: List<Notes>) :RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

    class NoteViewHolder (itemview: View) : RecyclerView.ViewHolder(itemview) {

        val txtJudulNotes = itemview.findViewById<TextView>(R.id.txtJudulNotes)

        fun bind(notes: Notes){
            txtJudulNotes.text = notes.judul
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val note = notesList[position]
        holder.bind(note)

    }
}