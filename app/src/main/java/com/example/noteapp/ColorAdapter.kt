package com.example.noteapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(
    private val context: Context,
    private val colors: List<Int>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    inner class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val colorView: View = itemView.findViewById(R.id.colorView)

        fun bind(color: Int) {
            val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.square_rounded_corners)?.mutate()
            backgroundDrawable?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
            colorView.background = backgroundDrawable

            //Color gönderilecek

            itemView.setOnClickListener {
                Log.e("deneme","denem107")
                Log.e("deneme", color.toString())
                //Color gönderilecek
                val intent = Intent(context , NoteRecordActivity::class.java)
                Log.e("deneme", color.toString())
                Log.e("deneme","denem106")
                Log.e("deneme",Color.RED.toString())

                intent.putExtra("color", color)
                context.startActivity(intent)
                onItemClick(color) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.color_item, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color = colors[position]
        holder.bind(color)
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}