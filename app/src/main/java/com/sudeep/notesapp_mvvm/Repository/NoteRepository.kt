package com.sudeep.notesapp_mvvm.Repository

import androidx.lifecycle.LiveData
import com.sudeep.notesapp_mvvm.DAO.NoteDao
import com.sudeep.notesapp_mvvm.Entity.Note

class NoteRepository(private  val noteDao: NoteDao ) {

    val allNotes : LiveData<List<Note>> =noteDao.getALlNote()

    suspend fun insert(note:Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
}