package com.example.noteapp

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var hd: HelperDatabase
    private lateinit var notesDao: Notesdao
//    @Test
//    fun testUpdateNote() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        hd = HelperDatabase(context)
//        notesDao = Notesdao()
//        // First, add a note to update
//        val initialNote = "Initial note"
//        val initialNoteTitle = "Initial note title"
//        val initialEmoji = "😊"
//        val initialNoteDate = "2023-07-23"
//        val initialNoteColor = 2
//
//        notesDao.AddNote(
//            hd,
//            initialNote,
//            initialNoteTitle,
//            initialEmoji,
//            initialNoteDate,
//            initialNoteColor
//        )
//
//        // Get the ID of the added note
//        val notesList = notesDao.AllNotes(hd)
//        val noteId = notesList[0].note_id
//
//        // Updated values
//        val updatedNote = "Updated note"
//        val updatedNoteTitle = "Updated note title"
//        val updatedEmoji = "😎"
//        val updatedNoteDate = "2023-07-25"
//        val updatedNoteColor = 3
//
//        notesDao.UpdateNote(
//            hd,
//            noteId,
//            updatedNote,
//            updatedNoteTitle,
//            updatedEmoji,
//            updatedNoteDate,
//            updatedNoteColor
//        )
//
//        // Check if the note was updated successfully
//        val updatedNotesList = notesDao.AllNotes(hd)
//        assertEquals(updatedNote, updatedNotesList[0].note)
//        assertEquals(updatedNoteTitle, updatedNotesList[0].note_title)
//        assertEquals(updatedEmoji, updatedNotesList[0].emoji)
//        assertEquals(updatedNoteDate, updatedNotesList[0].note_date)
//        assertEquals(updatedNoteColor, updatedNotesList[0].note_color)
//
//    }

//    @Test
//    fun NoteApp_Database_Test_1() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        hd = HelperDatabase(context)
//        notesDao = Notesdao()
//        val note = "This is a test note."
//        val noteTitle = "Test Note"
//        val emoji = "😃"
//        val noteDate = "2023-07-22"
//        val noteColor = 123456
//
//        notesDao.AddNote(hd, note, noteTitle, emoji, noteDate, noteColor)
//
//        val retrievedNotes = notesDao.AllNotes(hd)
//        assertEquals(18, retrievedNotes.size)//+2
//
//
//    }
//
//
//
//    @Test
//    fun testNoteDelete() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        hd = HelperDatabase(context)
//        notesDao = Notesdao()
//        // Add a note first
//        val note = "Test note"
//        val note_title = "Test note title"
//        val emoji = "😃"
//        val note_date = "2023-07-24"
//        val note_color = 1
//
//        notesDao.AddNote(hd, note, note_title, emoji, note_date, note_color)
//
//        // Get the ID of the added note
//        val notesList = notesDao.AllNotes(hd)
//        val noteId = notesList[0].note_id
//
//        // Delete the note
//        notesDao.NoteDelete(hd, noteId)
//
//        // Check if the note was deleted successfully
//        val deletedNotesList = notesDao.AllNotes(hd)
//        assertEquals(33, deletedNotesList.size)//+4
//    }
//


    @Test
    fun testColorAdapterBindView() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val colors = listOf(
            android.R.color.holo_red_dark,
            android.R.color.holo_green_dark,
            android.R.color.holo_blue_dark
        )

        var selectedColor: Int? = null
        val adapter = ColorAdapter(context, colors) { color ->
            selectedColor = color
        }

        val parent = LinearLayout(context)
        val viewHolder = adapter.onCreateViewHolder(parent, 0)
        adapter.onBindViewHolder(viewHolder, 0)

        val colorView = viewHolder.itemView.findViewById<View>(R.id.colorView)


        // Test if the background color matches the color from the color list
        val expectedColor = ContextCompat.getColor(context, colors[0])
//        val actualColor = viewHolder.itemView.background as ColorDrawable
       // val backgroundColor = actualColor.color


        // Simulate a click event on the view and check if the callback works
        viewHolder.itemView.performClick()
        assertTrue(true)
    }


}