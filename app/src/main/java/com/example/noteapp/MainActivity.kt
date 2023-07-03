package com.example.noteapp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbar.title = ""

        // Custom title
        val textCustomTitle: TextView = findViewById(R.id.custom_title)



        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId;
        if (id == R.id.toolbar_theme){
            Toast.makeText(this,"toolbar_theme",Toast.LENGTH_SHORT).show()
        }
        if (id == R.id.toolbar_settings){
            Toast.makeText(this,"toolbar_settings",Toast.LENGTH_SHORT).show()
        }
        return true
    }
}