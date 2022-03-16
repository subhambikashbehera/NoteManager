package com.subhambnikash.roompractice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [NoteTable::class], version = 2, exportSchema = true)
abstract class DataBaseOperation : RoomDatabase() {
    abstract val daoObj: DaoCustom
    companion object {
        @Volatile
        var dbInstance: DataBaseOperation? = null
        fun getInstance(context: Context):DataBaseOperation {
            synchronized(this) {
                if (dbInstance == null) {
                    dbInstance = Room.databaseBuilder(context.applicationContext, DataBaseOperation::class.java, "noteDb").fallbackToDestructiveMigration().build()
                }
            }
            return dbInstance!!
        }
    }
}