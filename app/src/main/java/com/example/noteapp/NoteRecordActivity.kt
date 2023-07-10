package com.example.noteapp

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.ActivityNoteRecordBinding
import com.google.android.material.snackbar.Snackbar


class NoteRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteRecordBinding
    private lateinit var colorPickerDialog: AlertDialog
    private lateinit var content: ConstraintLayout
    private lateinit var vt: HelperDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNoteRecordBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //Veritabanı
        vt = HelperDatabase(this)

        binding.saveButton.setOnClickListener {
            //Veritabanı
            val note_title=binding.editTextText.text.toString().trim()
            val note=binding.editTextText2.text.toString().trim()
           //Emoji eklenecek val emoji=binding..text.toString().trim()
            //val note_date=binding.editTextText.text.toString().trim()
            // Color kısmı düzenlenecek val note_color=binding.editTextText.text.toString().trim()

            if(TextUtils.isEmpty((note_title))){
                Snackbar.make(binding.toolbar,"Note Başlığı Giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty((note))){
                Snackbar.make(binding.toolbar,"Note Giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            Notesdao().AddNote(vt,note,note_title,null,null,null)
            startActivity(Intent(this@NoteRecordActivity, MainActivity::class.java))
            finish()

        }


        content = findViewById(R.id.contentRecord)
        val colorPickerButton: Button = findViewById(R.id.ColorPickerCardRecord)
        colorPickerButton.setOnClickListener { showColorPickerDialog() }


    }


    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    private fun showColorPickerDialog() {

        val colors = listOf(
            Color.CYAN,
            Color.rgb(179, 157, 219),
            Color.MAGENTA,
            Color.rgb(245, 245, 220),
            Color.YELLOW,
            Color.rgb(169, 169, 169),
            Color.GREEN,
            Color.rgb(244, 164, 96),
            Color.BLUE,
            Color.RED,
            Color.rgb(255, 228, 181),
            Color.rgb(72, 61, 139),
            Color.rgb(205, 92, 92),
            Color.rgb(255, 165, 0),
            Color.rgb(102, 205, 170)
        )

        val numColumns = 5 // Desired number of columns
        val padding = dpToPx(15) // Convert 15 dp to pixels
        val spacing = dpToPx(15) // Set the spacing between items in dp

        val recyclerView = RecyclerView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            layoutManager = GridLayoutManager(this@NoteRecordActivity, numColumns)
            setPadding(padding, dpToPx(20), padding, padding) // Convert padding to pixels
            adapter = ColorAdapter(this@NoteRecordActivity, colors) { selectedColor ->
                // Do something with the selected color

                // Change Background Color
                content.setBackgroundColor(
                    ColorUtils.blendARGB(
                        selectedColor,
                        Color.BLACK,
                        0.3f
                    )
                ) // Make it darker
                // Change Status Bar Background Color
                window.statusBarColor =
                    ColorUtils.blendARGB(selectedColor, Color.BLACK, 0.1f) // Make it darker
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
}