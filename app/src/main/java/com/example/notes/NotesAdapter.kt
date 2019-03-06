package com.example.notes

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text


class NotesAdapter(var notes: ArrayList<Note>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    interface OnNoteClickListener {
        fun onNoteClick(position: Int)
        fun onNoteLongClick(position: Int)
    }

    private var onNoteClickListener: OnNoteClickListener? = null

    fun setOnNoteClickListener(listener: OnNoteClickListener) {
        this.onNoteClickListener = listener
    }

    override fun onBindViewHolder(p0: NotesViewHolder, p1: Int) {
        val note = notes[p1]
        p0.title.text = note.title
        p0.description.text = note.description
        p0.dayOfWeek.text = note.dayOfWeek
        p0.title.setBackgroundColor(
            when (note.priority) {
                1 -> p0.itemView.resources.getColor(android.R.color.holo_red_light)
                2 -> p0.itemView.resources.getColor(android.R.color.holo_orange_light)
                3 -> p0.itemView.resources.getColor(android.R.color.holo_green_light)
                else -> p0.itemView.resources.getColor(android.R.color.white)
            }
        )
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NotesViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.note_item, p0, false)
        return NotesViewHolder(view)

    }

    override fun getItemCount(): Int {
        return notes.size
    }


    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.textViewTitle)
        var description: TextView = itemView.findViewById(R.id.textViewDescription)
        var dayOfWeek: TextView = itemView.findViewById(R.id.textViewDay)

        init {
            itemView.setOnClickListener { onNoteClickListener?.onNoteClick(adapterPosition) }
            itemView.setOnLongClickListener {
                onNoteClickListener?.onNoteLongClick(adapterPosition)
                return@setOnLongClickListener true
            }
        }



    }

}