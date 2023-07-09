package com.example.noteapp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.noteapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notesLists:ArrayList<Notes>
    private lateinit var adapter: NotsAdapter
    private lateinit var colorPickerDialog: AlertDialog
    private lateinit var content: ConstraintLayout

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


        content = findViewById(R.id.content)
        val colorPickerButton: Button = findViewById(R.id.colorPicker)
        colorPickerButton.setOnClickListener { showColorPickerDialog() }



        binding.changeViewButton.setOnClickListener {
            if (binding.changeViewButton.background.equals(getDrawable(R.drawable.baseline_grid_view_24))){

                var linearLayoutManager: LinearLayoutManager
                binding.rv.layoutManager = GridLayoutManager(this,2)
                binding.rv.setHasFixedSize(true)
            }
            else{
                binding.changeViewButton.background = getDrawable(R.drawable.baseline_view_list_24)
            }
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




    private fun showColorPickerDialog() {

        val colors = listOf(
            Color.CYAN, Color.rgb(179, 157, 219), Color.MAGENTA, Color.rgb(245, 245, 220), Color.YELLOW,
            Color.rgb(169, 169, 169), Color.GREEN, Color.rgb(244, 164, 96), Color.BLUE, Color.RED,
            Color.rgb(255, 228, 181), Color.rgb(72, 61, 139), Color.rgb(205, 92, 92), Color.rgb(255, 165, 0), Color.rgb(102, 205, 170)
        )

        val numColumns = 5 // Desired number of columns
        val padding = dpToPx(15) // Convert 15 dp to pixels
        val spacing = dpToPx(15) // Set the spacing between items in dp

        val recyclerView = RecyclerView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            layoutManager = GridLayoutManager(this@MainActivity, numColumns)
            setPadding(padding, dpToPx(20), padding, padding) // Convert padding to pixels
            adapter = ColorAdapter(this@MainActivity, colors) { selectedColor ->
                // Do something with the selected color

                // Change Background Color
                content.setBackgroundColor(ColorUtils.blendARGB(selectedColor, Color.BLACK, 0.3f)) // Make it darker
                // Change Status Bar Background Color
                window.statusBarColor = ColorUtils.blendARGB(selectedColor, Color.BLACK, 0.1f) // Make it darker
                // Change the App Bar Background Color
                supportActionBar?.setBackgroundDrawable(ColorDrawable(selectedColor))

                colorPickerDialog.dismiss()
            }
            addItemDecoration(GridSpacingItemDecoration(numColumns, spacing, true))
        }

        colorPickerDialog = AlertDialog.Builder(this, R.style.ShowAlertDialogTheme)
            .setTitle("Choose a color")
            .setView(recyclerView)
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        colorPickerDialog.show()
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}