package com.example.roomdatabaselecture.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabaselecture.data.model.Project
import com.example.roomdatabaselecture.data.model.Task

@Database(entities = [Project::class,Task::class], version = 1)
abstract class ProjectsDatabase : RoomDatabase() {

    abstract fun getProjectDao(): ProjectsDao
    abstract fun getTasksDao():TaskDao
    companion object {
        fun buildDatabase(context: Context): ProjectsDatabase =
            Room.databaseBuilder(context, ProjectsDatabase::class.java, "cars_db").build()
    }
}