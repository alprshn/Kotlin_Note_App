package com.example.noteapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
            val note_title = binding.editTextText.text.toString().trim()
            val note = binding.editTextText2.text.toString().trim()
            val emoji = binding.textEmoji.text.toString().trim()

            val cardColor = intent.getIntExtra("color", 0)
            Log.e("deneme", cardColor.toString())
            //Emoji eklenecek val emoji=binding..text.toString().trim()
            //val note_date=binding.editTextText.text.toString().trim()
            // Color kısmı düzenlenecek val note_color=binding.editTextText.text.toString().trim()

            if (TextUtils.isEmpty((note_title))) {
                Snackbar.make(binding.toolbar, "Note Başlığı Giriniz", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty((note))) {
                Snackbar.make(binding.toolbar, "Note Giriniz", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }




            Log.e("deneme", "deneme1")
            Notesdao().AddNote(vt, note, note_title, emoji, "2014", cardColor, "null")
            Log.e("deneme", "deneme2")
            startActivity(Intent(this@NoteRecordActivity, MainActivity::class.java))
            finish()

        }

        //For Color
        content = findViewById(R.id.contentRecord)
        val colorPickerButton: Button = findViewById(R.id.ColorPickerCardRecord)
        colorPickerButton.setOnClickListener { showColorPickerDialog() }

        binding.emojiRecordButton.setOnClickListener {
            showAlertDialogButtonClicked()
        }

    }


    fun showAlertDialogButtonClicked() {
        // Create an alert builder
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Name")

        // set the custom layout
        val customLayout: View = layoutInflater.inflate(R.layout.emoji_layout, null)
        builder.setView(customLayout)

        // add a button
        builder.setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->
            // send data from the AlertDialog to the Activity
            val editText = customLayout.findViewById<EditText>(R.id.addEmoji)
            sendDialogDataToActivity(editText.text.toString())
        }
        // create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }


    // Do something with the data coming from the AlertDialog
    private fun sendDialogDataToActivity(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
        binding.textEmoji.text = data
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