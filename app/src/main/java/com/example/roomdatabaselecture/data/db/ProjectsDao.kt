package com.example.roomdatabaselecture.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdatabaselecture.data.model.Project

@Dao
interface ProjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project)

    @Query("SELECT * FROM project_table")
    suspend fun getAllProjects(): List<Project>
}