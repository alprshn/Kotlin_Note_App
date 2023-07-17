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
import com.example.noteapp.databinding.ActivityDetailsBinding
import com.example.noteapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var colorPickerDialog: AlertDialog
    private lateinit var content: ConstraintLayout
    private lateinit var getNote: Notes
    private lateinit var hd: HelperDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.contentDetails)

        val notes = intent.getSerializableExtra("object") as Notes

        hd = HelperDatabase(this)
        Log.e("detay", "detay5")
        binding.editTitle.setText(notes.note_title)
        Log.e("detay", "detay6")
        binding.editNote.setText(notes.note)
        Log.e("detay", "detay7")
        binding.detailsEmojiText.setText(notes.emoji)

        Log.e("detay", "detay8")

        binding.detailsEmojiPicker.setOnClickListener {
            showAlertDialogButtonClicked()
        }
        Log.e("detay", "detay2")
        binding.EditButton.setOnClickListener {

            val note_title = binding.editTitle.text.toString()
            Log.e("deneme", "detay1")
            val note = binding.editNote.text.toString()
            Log.e("deneme", "detay9")
            val emoji = binding.detailsEmojiText.text.toString()
            Log.e("deneme", "detay10")
            val constraintLayout = findViewById<ConstraintLayout>(R.id.contentDetails)
            Log.e("deneme", "detay11")
            val currentTime = getCurrentTime()
            Log.e("deneme", "detay12")
            val cardColor = (constraintLayout.background as? ColorDrawable)?.color ?: 0
            Log.e("deneme", "detay3")


            Notesdao().UpdateNote(
                hd,
                notes.note_id,
                note,
                note_title,
                emoji,
                currentTime,
                cardColor
            )

            Log.e("deneme", "Burası çalışmıyor")



            Log.e("deneme", "detay4")




            startActivity(Intent(this@DetailsActivity, MainActivity::class.java))
            finish()
        }

        content = findViewById(R.id.contentDetails)
        val colorPickerButton: Button = findViewById(R.id.detailsColorPicker)
        colorPickerButton.setOnClickListener { showColorPickerDialog() }
    }


    private fun getCurrentTime(): String {
        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
        return dateFormat.format(currentTime)
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

    private fun sendDialogDataToActivity(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
        binding.detailsEmojiText.text = data
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
            layoutManager = GridLayoutManager(this@DetailsActivity, numColumns)
            setPadding(padding, dpToPx(20), padding, padding) // Convert padding to pixels
            adapter = ColorAdapter(this@DetailsActivity, colors) { selectedColor ->
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