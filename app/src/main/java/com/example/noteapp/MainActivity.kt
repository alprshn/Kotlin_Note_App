package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.ActivityMainBinding
/**
 * The main activity of the Note App.
 *
 * This activity displays a list of notes in a RecyclerView.
 * Users can switch between list view and grid view for the notes.
 * The notes are fetched from a database and displayed using the `NotsAdapter`.
 * Users can also add new notes by clicking the floating action button.
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notesLists: ArrayList<Notes>
    private lateinit var adapter: NotsAdapter
    private lateinit var vt: HelperDatabase
    var selector: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this)
        vt = HelperDatabase(this)
        notesLists = Notesdao().AllNotes(vt)
        adapter = NotsAdapter(this, notesLists)
        binding.rv.adapter = adapter
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, NoteRecordActivity::class.java))
        }
        ChangeViev()
    }
    /**
     * Switches between list view and grid view for the notes.
     * This function is called when the user clicks the change view button.
     */
    fun ChangeViev() {
        selector = true
        binding.changeViewButton.setOnClickListener {
            if (selector) {
                binding.changeViewButton.setBackgroundResource(R.drawable.baseline_view_list_24)
                var linearLayoutManager: LinearLayoutManager
                binding.rv.layoutManager = GridLayoutManager(this, 2)
                binding.rv.setHasFixedSize(true)
                selector = false
            } else {
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