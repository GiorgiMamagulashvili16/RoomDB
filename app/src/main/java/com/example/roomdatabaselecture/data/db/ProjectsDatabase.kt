package com.example.roomdatabaselecture.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabaselecture.data.model.Project

@Database(entities = [Project::class], version = 1)
abstract class ProjectsDatabase : RoomDatabase() {

    abstract fun getProjectDao(): ProjectsDao

    companion object {
        fun buildDatabase(context: Context): ProjectsDatabase =
            Room.databaseBuilder(context, ProjectsDatabase::class.java, "cars_db").build()
    }
}