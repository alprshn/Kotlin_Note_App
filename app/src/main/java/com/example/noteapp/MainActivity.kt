package com.example.noteapp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.noteapp.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notesLists: ArrayList<Notes>
    private lateinit var adapter: NotsAdapter
    private lateinit var colorPickerDialog: AlertDialog
    private lateinit var content: ConstraintLayout
    private lateinit var vt: HelperDatabase
    lateinit var secondCard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)




        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this)


        vt = HelperDatabase(this)
        Log.e("deneme","deneme7")
        notesLists = Notesdao().AllNotes(vt)
        Log.e("deneme","deneme6")
        adapter = NotsAdapter(this, notesLists)
        Log.e("deneme","deneme8")
        binding.rv.adapter = adapter


        Log.e("deneme","deneme9")
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, NoteRecordActivity::class.java))
        }



        ChangeViev()


    }


    fun ChangeViev() {
        var selector: Boolean
        selector = true

        binding.changeViewButton.setOnClickListener {
            if (selector) {

                binding.changeViewButton.setBackgroundResource(R.drawable.baseline_view_list_24)
                var linearLayoutManager: LinearLayoutManager
                binding.rv.layoutManager = GridLayoutManager(this, 2)
                binding.rv.setHasFixedSize(true)
                selector = false
            } else {
                Log.e("if", "if")
                binding.changeViewButton.setBackgroundResource(R.drawable.baseline_grid_view_24)
                var linearLayoutManager: LinearLayoutManager
                binding.rv.layoutManager = LinearLayoutManager(this)
                binding.rv.setHasFixedSize(true)
                selector = true
            }


        }
    }




    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}