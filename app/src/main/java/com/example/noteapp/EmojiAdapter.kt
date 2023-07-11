package com.example.noteapp

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class EmojiAdapter(
    private val context: Context,
    private val emojies: List<Int>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder>() {

    inner class EmojiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val emojiView: View = itemView.findViewById(R.id.emojiView)

        fun bind(emoji: Int) {

            val unicode = 0x1F60A
            val emojiee = getEmoji(unicode)
            val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.square_rounded_corners)?.mutate()
            backgroundDrawable?.colorFilter = PorterDuffColorFilter(emoji, PorterDuff.Mode.SRC_IN)
            emojiView.background = backgroundDrawable
            itemView.setOnClickListener { onItemClick(emoji) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.emoji_item, parent, false)
        return EmojiViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmojiViewHolder, position: Int) {
        val emoji = emojies[position]
        holder.bind(emoji)
    }


    fun getEmoji(uni: Int): String {
        return String(Character.toChars(uni))
    }


    override fun getItemCount(): Int {
        return emojies.size
    }
}