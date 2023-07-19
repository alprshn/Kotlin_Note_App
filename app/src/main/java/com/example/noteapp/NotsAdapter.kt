package com.example.noteapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NotsAdapter(private val mContext: Context, private val notesList: List<Notes>) :
    RecyclerView.Adapter<NotsAdapter.CardDesignHolder>() {
    private lateinit var hd:HelperDatabase
    inner class CardDesignHolder(design: View) : RecyclerView.ViewHolder(design) {
        var notesCard: CardView
        var textViewEmoji: TextView
        var textViewNoteTitle: TextView
        var textViewNoteDate: TextView
        var textViewNote: TextView
        init {
            notesCard = design.findViewById(R.id.CardView)
            textViewEmoji = design.findViewById(R.id.emoji)
            textViewNoteTitle = design.findViewById(R.id.noteTitle)
            textViewNoteDate = design.findViewById(R.id.noteDateText)
            textViewNote = design.findViewById(R.id.noteText)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val design = LayoutInflater.from(mContext).inflate(R.layout.card_design, parent, false)
        return CardDesignHolder(design)
    }
    override fun getItemCount(): Int {
        return notesList.size
    }
    fun getEmoji(uni: Int): String {
        return String(Character.toChars(uni))
    }
    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        var cardColor: Int
        val notes = notesList.get(position)
        cardColor = notes.note_color
        holder.notesCard.setCardBackgroundColor(cardColor)
        //for Emoji Code
        holder.textViewEmoji.text = notes.emoji
        holder.textViewNoteTitle.text = notes.note_title
        holder.textViewNoteDate.text = notes.note_date
        holder.textViewNote.text = notes.note

        holder.notesCard.setOnClickListener {
            val intent = Intent(mContext, DetailsActivity::class.java)
            intent.putExtra("object", notes)
            mContext.startActivity(intent)
        }
        val popupMenu = PopupMenu(mContext, holder.notesCard)
        popupMenu.inflate(R.menu.context_menu)

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete -> {
                    hd = HelperDatabase(mContext)
                    Notesdao().NoteDelete(hd,notes.note_id)
                    Toast.makeText(mContext, "Shared", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    true
                }
            }
        }
        // event on long press on image
        holder.notesCard.setOnLongClickListener {
            try {
                val popup = PopupMenu::class.java.getDeclaredField("mPopup")
                popup.isAccessible = true
                val menu = popup.get(popupMenu)
                menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(menu, true)
            } catch (e: Exception) {
            } finally {
                popupMenu.show()
            }
            true
        }
    }
}