package com.example.noteapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class HelperDatabase(context: Context) : SQLiteOpenHelper(context,"notes.sqlite", null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE notes(note_id INTEGER PRIMARY KEY AUTOINCREMENT,emoji TEXT, note_title TEXT,note_date TEXT,main_color TEXT,note_color INTEGER,note TEXT );")
        Log.e("deneme","deneme3")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS notes")
        Log.e("deneme","deneme4")
        onCreate(db)
        Log.e("deneme","deneme5")
    }
}