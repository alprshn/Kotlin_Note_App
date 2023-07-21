package com.example.noteapp

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
/**
 * Adapter for the color selector RecyclerView.
 *
 * This adapter is responsible for displaying a list of colors in a RecyclerView.
 * Users can select a color by clicking on a color item.
 * The selected color is passed back to the calling activity/fragment through the `onItemClick` callback.
 *
 * @param context the context of the calling activity/fragment.
 * @param colors the list of colors to be displayed in the RecyclerView.
 * @param onItemClick the callback function to be invoked when a color item is clicked.
 */
class ColorAdapter(
    private val context: Context,
    private val colors: List<Int>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    inner class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val colorView: View = itemView.findViewById(R.id.colorView)
        /**
         * Binds a color to the color item view.
         *
         * @param color the color value to be displayed.
         */
        fun bind(color: Int) {
            val backgroundDrawable =
                ContextCompat.getDrawable(context, R.drawable.square_rounded_corners)?.mutate()
            backgroundDrawable?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
            colorView.background = backgroundDrawable

            itemView.setOnClickListener {
                onItemClick(color)
            }
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