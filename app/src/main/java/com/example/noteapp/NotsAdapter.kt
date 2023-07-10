package com.example.noteapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NotsAdapter(private val mContext: Context, private val notesList: List<Notes>) :
    RecyclerView.Adapter<NotsAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(design: View) : RecyclerView.ViewHolder(design) {
        var notesCard: CardView
        var textViewEmoji: TextView
        var textViewNoteTitle: TextView
        var textViewNoteDate: TextView
        init {
            notesCard = design.findViewById(R.id.CardView)
            textViewEmoji = design.findViewById(R.id.emoji)
            textViewNoteTitle = design.findViewById(R.id.noteTitle)
            textViewNoteDate = design.findViewById(R.id.noteDateText)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val design = LayoutInflater.from(mContext).inflate(R.layout.card_design, parent, false)
        return CardDesignHolder(design)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        var cardColor:String
        Log.e("deneme","deneme25")
        val notes = notesList.get(position)
        Log.e("deneme","deneme26")
        cardColor = notes.note_color
        Log.e("deneme","deneme27")
        //holder.notesCard.setCardBackgroundColor(Color.parseColor(/* colorString = */ cardColor))
        //Log.e("deneme","deneme28")
        holder.textViewEmoji.text = notes.emoji
        Log.e("deneme","deneme29")
        holder.textViewNoteTitle.text = notes.note_title
        Log.e("deneme","deneme30")
        holder.textViewNoteDate.text = notes.note_date
        Log.e("deneme","deneme31")
        holder.notesCard.setOnClickListener {
            val intent = Intent(mContext, DetailsActivity::class.java)
            intent.putExtra("object", notes)
            mContext.startActivity(intent)
        }
    }
}