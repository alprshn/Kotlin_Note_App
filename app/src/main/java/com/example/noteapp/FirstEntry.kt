package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop


class FirstEntry : AppCompatActivity() {

    private lateinit var imageView: ImageView
    companion object{
        val IMAGE_REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_entry)


        val imageView = findViewById(R.id.entryImage) as ImageView

        val galleryImage = registerForActivityResult(ActivityResultContracts.GetContent(),{
            imageView.setImageURI(it)
        })

        imageView.setOnClickListener {
            galleryImage.launch("image/*")
        }
    }

}