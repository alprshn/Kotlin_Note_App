package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        Glide.with(this)
            .load(R.drawable.alpersahin)
            .transform(CircleCrop()) 
            .into(binding.imageView)

        //for Emoji Code
        val textView: TextView
        textView = findViewById(R.id.alper)
        val unicode = 0x1F60A
        val emoji = getEmoji(unicode)
        textView.setText(emoji)
    }

    //getEmoıji
    fun getEmoji(uni: Int): String {
        return String(Character.toChars(uni))
    }
}