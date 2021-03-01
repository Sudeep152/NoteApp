package com.sudeep.notesapp_mvvm.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sudeep.notesapp_mvvm.Entity.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insert(note:Note)

    @Delete
    suspend fun  delete(note: Note)

    @Query("SELECT * from notes_table order by id ASC")
    fun getALlNote():LiveData<List<Note>>
}