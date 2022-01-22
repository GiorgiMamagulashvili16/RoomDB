package com.example.roomdatabaselecture

import android.app.Application
import com.example.roomdatabaselecture.data.db.ProjectsDatabase

class App : Application() {
    companion object {
       private lateinit var ctx:App

        val db: ProjectsDatabase by lazy {
            ProjectsDatabase.buildDatabase(ctx)
        }
    }
    override fun onCreate() {
        super.onCreate()
        ctx = this
    }
}