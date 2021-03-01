package com.sudeep.notesapp_mvvm.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sudeep.notesapp_mvvm.Entity.Note
import com.sudeep.notesapp_mvvm.Repository.NoteRepository
import com.sudeep.notesapp_mvvm.RoomDatabase.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {
    val repo:NoteRepository
    val allNotes :LiveData<List<Note>>

    init {
        val dao =NoteDatabase.getDatabase(application).getNoteDao()
           repo= NoteRepository(dao)
        allNotes=repo.allNotes


    }
    fun deleteNote(note: Note)= viewModelScope.launch(Dispatchers.IO) {
          repo.delete(note)
    }
    fun insert(note: Note)=viewModelScope.launch (Dispatchers.IO ){
        repo.insert(note)
    }

}