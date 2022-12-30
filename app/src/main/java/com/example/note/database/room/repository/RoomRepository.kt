package com.example.note.database.room.repository

import androidx.lifecycle.LiveData
import com.example.note.database.DataBaseRepository
import com.example.note.database.room.dao.NoteRoomDao
import com.example.note.model.Note

class RoomRepository (private val noteRoomDao: NoteRoomDao) : DataBaseRepository {

    override val readAll: LiveData<List<Note>>
        get() = noteRoomDao.getAllNotes()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = note)
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)
    }


}