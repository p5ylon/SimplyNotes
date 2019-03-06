package com.example.notes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        var notes = arrayListOf<Note>()
        private val adapter = NotesAdapter(notes)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        recyclerViewNotes.adapter = adapter
        adapter.setOnNoteClickListener(object : NotesAdapter.OnNoteClickListener {
            override fun onNoteClick(position: Int) {
            }

            override fun onNoteLongClick(position: Int) {
//                removeNote(position)
            }
        })

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                return false
            }
            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                removeNote(p0.adapterPosition)
            }
        })

        itemTouchHelper.attachToRecyclerView(recyclerViewNotes)
    }

    private fun removeNote(position: Int) {
        notes.removeAt(position)
        adapter.notifyDataSetChanged()
    }


    fun onClickAddNote(view: View) {
        val addNoteIntent = Intent(this, AddNoteActivity::class.java)
        startActivity(addNoteIntent)

    }


}
