package com.subhambnikash.roompractice.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhambnikash.roompractice.db.NoteTable
import com.subhambnikash.roompractice.message.Event
import com.subhambnikash.roompractice.repo.RepositoryClass
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ViewModelMainActivity(private val repositoryClass: RepositoryClass):ViewModel() {

    val allNotes:LiveData<List<NoteTable>>

    val headingName=MutableLiveData<String>()
    val saveOrUpdateButtonText=MutableLiveData<String>()
    val clearOrDeleteButtonText=MutableLiveData<String>()
    val poetTittle=MutableLiveData<String?>()
    val poetDescription=MutableLiveData<String?>()
    private lateinit var updatedNoteTable:NoteTable

    private val message=MutableLiveData<Event<String>>()
    val messageEvent:LiveData<Event<String>>
    get() = message



    private var deleteOrUpdate=false

    init {
        saveOrUpdateButtonText.value="add"
        clearOrDeleteButtonText.value="delete all"
        headingName.value="Add poet here"
        allNotes=repositoryClass.getNote
    }

    fun saveOrUpdate(){



        if (deleteOrUpdate){
            updatedNoteTable.noteTittle=poetTittle.value.toString()
            updatedNoteTable.noteDescription=poetDescription.value.toString()
            updateNote(updatedNoteTable)
            poetTittle.value=null
            poetDescription.value=null
            clearOrDeleteButtonText.value="deleteAll"
            saveOrUpdateButtonText.value="add"
            deleteOrUpdate=false
            message.value=Event("updated")
        }else{
            insertNote(NoteTable(0,poetTittle.value.toString(),poetDescription.value.toString()))
            poetTittle.value=null
            poetDescription.value =null
            message.value=Event("inserted")
        }
    }

    fun deleteOrDeleteAll(){
        if (deleteOrUpdate){

            updatedNoteTable.noteTittle=poetTittle.value.toString()
            updatedNoteTable.noteDescription=poetDescription.value.toString()
            deleteNote(updatedNoteTable)

            poetTittle.value=null
            poetDescription.value=null
            clearOrDeleteButtonText.value="deleteAll"
            saveOrUpdateButtonText.value="add"
            deleteOrUpdate=false

            message.value= Event("deleted")
        }else{
            message.value=Event("not available now")
        }
    }


    fun deleteUpdateInit(noteTable: NoteTable){
        poetDescription.value=noteTable.noteDescription
        poetTittle.value=noteTable.noteTittle
        updatedNoteTable= noteTable
        clearOrDeleteButtonText.value="delete"
        saveOrUpdateButtonText.value="update"
        deleteOrUpdate=true
    }


    private fun insertNote(noteTable: NoteTable):Job=viewModelScope.launch {
        repositoryClass.insertNote(noteTable)
    }

    private fun updateNote(noteTable: NoteTable):Job=viewModelScope.launch {
        repositoryClass.updateNote(noteTable)
    }

   private fun deleteNote(noteTable: NoteTable):Job=viewModelScope.launch {
        repositoryClass.deleteNote(noteTable)
    }


}