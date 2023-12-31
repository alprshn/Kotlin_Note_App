package com.example.noteapp

import java.io.Serializable

data class Notes(
    var note_id: Int,
    var emoji: String,
    var note_title: String,
    var note_date: String,
    var note_color: Int,
    var note: String
) : Serializable {
}