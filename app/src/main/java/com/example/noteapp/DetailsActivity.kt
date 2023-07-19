package com.example.noteapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var colorPickerDialog: AlertDialog
    private lateinit var content: ConstraintLayout
    private lateinit var hd: HelperDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val notes = intent.getSerializableExtra("object") as Notes
        hd = HelperDatabase(this)
        binding.editTitle.setText(notes.note_title)
        binding.editNote.setText(notes.note)
        binding.detailsEmojiText.setText(notes.emoji)

        binding.detailsEmojiPicker.setOnClickListener {
            showAlertDialogButtonClicked()
        }
        binding.detailsBackButton.setOnClickListener {
            onBackPressed()
        }
        binding.EditButton.setOnClickListener {

            val note_title = binding.editTitle.text.toString()
            val note = binding.editNote.text.toString()
            val emoji = binding.detailsEmojiText.text.toString()
            val constraintLayout = findViewById<ConstraintLayout>(R.id.contentDetails)
            val currentTime = getCurrentTime()
            val cardColor = (constraintLayout.background as? ColorDrawable)?.color ?: 0
            Notesdao().UpdateNote(
                hd,
                notes.note_id,
                note,
                note_title,
                emoji,
                currentTime,
                cardColor
            )

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
            Color.rgb(255, 228, 181),
            Color.rgb(240, 230, 140),
            Color.rgb(0, 206, 209),
            Color.rgb(186, 85, 211),
            Color.rgb(107, 142, 35),
            Color.rgb(255, 160, 122),
            Color.rgb(75, 0, 130),
            Color.rgb(218, 165, 32),
            Color.rgb(70, 130, 180),
            Color.rgb(128, 0, 128),
            Color.rgb(46, 139, 87),
            Color.rgb(153, 50, 204),
            Color.rgb(139, 0, 0),
            Color.rgb(0, 250, 154),
            Color.rgb(39, 0, 139),
            Color.rgb(255, 69, 0),
            Color.rgb(85, 107, 47),
            Color.rgb(106, 90, 205),
            Color.rgb(39, 69, 19),
            Color.rgb(0, 128, 128)
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