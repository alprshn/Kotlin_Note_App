package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteapp.databinding.ActivityDetailsBinding
import com.example.noteapp.databinding.ActivityMainBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val notes =intent.getSerializableExtra("object") as Notes

        binding.editTitle.setText(notes.note_title)
        




        binding.EditButton.setOnClickListener {
            startActivity(Intent(this@DetailsActivity,MainActivity::class.java))
            finish()
        }
    }
}