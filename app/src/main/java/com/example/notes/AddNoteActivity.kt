package com.example.notes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)


    }

    fun onClickSaveNote(view: View) {
        val noteTitle = editTextTitle.text.toString().trim()
        val noteDescription = editTextDescription.text.toString().trim()
        val noteDayOfWeek = spinnerDaysOfWeek.selectedItem.toString()
        val notePriority = findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId).tag.toString().toInt()

        val note = Note(noteTitle, noteDescription, noteDayOfWeek, notePriority)

        MainActivity.notes.add(note)

        startActivity(Intent(this, MainActivity::class.java))

    }
}
