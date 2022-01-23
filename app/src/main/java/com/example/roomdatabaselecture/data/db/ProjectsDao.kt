package com.example.roomdatabaselecture.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdatabaselecture.data.model.Project
import com.example.roomdatabaselecture.data.model.ProjectWithTasks
import com.example.roomdatabaselecture.data.model.Task

@Dao
interface ProjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project)

    @Query("SELECT * FROM project_table")
    suspend fun getAllProjects(): List<Project>

    @Query("SELECT * FROM project_table WHERE id == :projectId")
    suspend fun getProjectById(projectId: Int): Project

    @Query("SELECT * FROM project_table WHERE id = :projectId")
    suspend fun getTasksByProjectId(projectId: Int): ProjectWithTasks

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Task::class)
    suspend fun insertTask(task: Task)

}