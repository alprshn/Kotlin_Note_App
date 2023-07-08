package com.example.noteapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notesLists:ArrayList<Notes>
    private lateinit var adapter: NotsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        Glide.with(this)
            .load("https://external-preview.redd.it/yeni-mit-ba%C5%9Fkan%C4%B1-i%CC%87brahim-kal%C4%B1n-profil-foto%C4%9Fraf%C4%B1n%C4%B1-v0-nggRZ7poIIut9AKU26ebcMHE0dlqyHvZCNF80ZJU1Yg.jpg?auto=webp&s=76ca81c4df7b9c2128d1e7821aa00495dbc87f97")
            .transform(CircleCrop())
            .into(binding.imageView)

        //for Emoji Code

        val unicode = 0x1F60A
        val emoji = getEmoji(unicode)


        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this)

        notesLists = ArrayList()

        val n1 = Notes(1,emoji,"Filmler Note Listesi","12 Haziran","null","null","null","null")
        notesLists.add(n1)
        notesLists.add(n1)
        notesLists.add(n1)
        notesLists.add(n1)
        notesLists.add(n1)
        notesLists.add(n1)
        notesLists.add(n1)
        notesLists.add(n1)
        notesLists.add(n1)
        notesLists.add(n1)
        notesLists.add(n1)
        notesLists.add(n1)


        adapter = NotsAdapter(this,notesLists)
        binding.rv.adapter = adapter
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this@MainActivity,NoteRecordActivity::class.java))
        }





    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
    //getEmoıji
    fun getEmoji(uni: Int): String {
        return String(Character.toChars(uni))
    }
}