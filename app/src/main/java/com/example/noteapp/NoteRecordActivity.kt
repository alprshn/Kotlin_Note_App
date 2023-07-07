package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteapp.databinding.ActivityNoteRecordBinding


class NoteRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNoteRecordBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.saveButton
    }
}