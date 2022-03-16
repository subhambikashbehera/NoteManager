package com.subhambnikash.roompractice.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao


@Dao
interface DaoCustom {

    @Insert
    suspend fun insertNote(noteTable: NoteTable)

    @Update
    suspend fun updateNote(noteTable: NoteTable)

    @Delete
    suspend fun deleteNote(noteTable: NoteTable)

    @Query("SELECT * FROM noteTable")
     fun getNote():LiveData<List<NoteTable>>


}