package com.example.noteapp

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity

class Notesdao {

    private lateinit var adapter: NotsAdapter
    private lateinit var context: Context
    val notesList = ArrayList<Notes>()


    fun AllNotes(hd: HelperDatabase): ArrayList<Notes> {
        val db = hd.writableDatabase
        val c = db.rawQuery("SELECT*FROM notes", null)
        Log.e("deneme","deneme11")


        while (c.moveToNext()){
            Log.e("deneme","deneme20")
            val note = Notes(c.getInt(c.getColumnIndex("note_id"))
            ,c.getString(c.getColumnIndex("emoji"))
            ,c.getString(c.getColumnIndex("note_title"))
            ,c.getString(c.getColumnIndex("note_date"))
            ,c.getInt(c.getColumnIndex("note_color"))
            ,c.getString(c.getColumnIndex("note"))
            ,c.getInt(c.getColumnIndex("main_color")))
            Log.e("deneme","deneme23")
            notesList.add(note)
            Log.e("deneme","deneme10")
        }
        Log.e("deneme","deneme105")
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
        main_color: Int
    ) {
        val db = hd.writableDatabase
        val values = ContentValues()
        values.put("emoji", emoji)
        values.put("note_title", note_title)
        values.put("note_date", note_date)
        Log.e("deneme","deneme103")
        values.put("note_color", note_color)
        Log.e("deneme","deneme104")
        values.put("note", note)
        values.put("main_color",main_color)
        db.insertOrThrow("notes", null, values)
        Log.e("deneme","deneme14")
        db.close()
    }


    fun UpdateNote(
        hd: HelperDatabase,
        note_id: Int,
        note: String,
        note_title: String,
        emoji: String,
        note_date: String,
        note_color: Int,
        main_color: Int
    ) {
        Log.e("deneme","detay524")
        val db = hd.writableDatabase
        val values = ContentValues()
        Log.e("deneme","deneme525")
        values.put("note", note)
        values.put("note_title", note_title)
        values.put("emoji", emoji)
        values.put("note_date", note_date)
        values.put("note_color", note_color)
        values.put("main_color",main_color)
        Log.e("deneme","deneme526")
        db.update("notes", values, "note_id=?", arrayOf(note_id.toString()))
        Log.e("deneme","deneme57")
        db.close()
    }
}