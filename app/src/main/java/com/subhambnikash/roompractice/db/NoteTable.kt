package com.subhambnikash.roompractice.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "noteTable")
data class NoteTable(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "noteTittle")
    var noteTittle:String,
    @ColumnInfo(name = "noteDescription")
    var noteDescription:String

)