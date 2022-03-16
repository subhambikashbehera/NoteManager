package com.subhambnikash.roompractice.repo

import androidx.lifecycle.LiveData
import com.subhambnikash.roompractice.db.DaoCustom
import com.subhambnikash.roompractice.db.NoteTable

class RepositoryClass(private val dao: DaoCustom) {

    val getNote:LiveData<List<NoteTable>> = dao.getNote()


    suspend fun insertNote(noteTable: NoteTable){
        dao.insertNote(noteTable)
    }


    suspend fun updateNote(noteTable: NoteTable){
        dao.updateNote(noteTable)
    }


    suspend fun deleteNote(noteTable: NoteTable){
        dao.deleteNote(noteTable)
    }


}