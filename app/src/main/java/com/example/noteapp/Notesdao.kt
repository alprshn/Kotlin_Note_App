package com.example.noteapp

import android.content.ContentValues
import android.content.Context

class Notesdao {
    private lateinit var adapter: NotsAdapter
    private lateinit var context: Context
    val notesList = ArrayList<Notes>()
    fun AllNotes(hd: HelperDatabase): ArrayList<Notes> {
        val db = hd.writableDatabase
        val c = db.rawQuery("SELECT*FROM notes", null)
        while (c.moveToNext()) {
            val note = Notes(
                c.getInt(c.getColumnIndex("note_id")),
                c.getString(c.getColumnIndex("emoji")),
                c.getString(c.getColumnIndex("note_title")),
                c.getString(c.getColumnIndex("note_date")),
                c.getInt(c.getColumnIndex("note_color")),
                c.getString(c.getColumnIndex("note"))
            )
            notesList.add(note)
        }
        return notesList
    }

    fun NoteDelete(hd: HelperDatabase, note_id: Int) {
        val db = hd.writableDatabase
        db.delete("notes", "note_id=?", arrayOf(note_id.toString()))

        db.close()

    }

    fun AddNote(
        hd: HelperDatabase,
        note: String,
        note_title: String,
        emoji: String,
        note_date: String,
        note_color: Int,
    ) {
        val db = hd.writableDatabase
        val values = ContentValues()
        values.put("emoji", emoji)
        values.put("note_title", note_title)
        values.put("note_date", note_date)
        values.put("note_color", note_color)
        values.put("note", note)
        db.insertOrThrow("notes", null, values)
        db.close()
    }

    fun UpdateNote(
        hd: HelperDatabase,
        note_id: Int,
        note: String,
        note_title: String,
        emoji: String,
        note_date: String,
        note_color: Int
    ) {
        val db = hd.writableDatabase
        val values = ContentValues()
        values.put("note", note)
        values.put("note_title", note_title)
        values.put("emoji", emoji)
        values.put("note_date", note_date)
        values.put("note_color", note_color)
        db.update("notes", values, "note_id=?", arrayOf(note_id.toString()))
        db.close()
    }
}