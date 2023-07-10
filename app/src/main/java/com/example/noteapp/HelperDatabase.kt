package com.example.noteapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HelperDatabase(context: Context) : SQLiteOpenHelper(context,"notlar.sqlite", null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE notes(not_id INTEGER PRIMARY KEY AUTOINCREMENT,emoji TEXT, note_title TEXT,note_date TEXT,main_color TEXT,note_color TEXT,note TEXT );")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(p0)
    }
}