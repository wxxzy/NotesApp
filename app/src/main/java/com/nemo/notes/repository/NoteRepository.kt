package com.nemo.notes.repository

import com.nemo.notes.database.NoteDao
import com.nemo.notes.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) = noteDao.insert(note)

    suspend fun delete(noteId: Long) = noteDao.delete(noteId)
}