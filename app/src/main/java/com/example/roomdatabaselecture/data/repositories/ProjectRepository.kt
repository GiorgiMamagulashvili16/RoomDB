package com.example.roomdatabaselecture.data.repositories

import com.example.roomdatabaselecture.data.model.Project
import com.example.roomdatabaselecture.data.model.ProjectWithTasks
import com.example.roomdatabaselecture.data.model.Task

interface ProjectRepository {
    suspend fun insertProject(project: Project)
    suspend fun getAllProject(): List<Project>
    suspend fun getProjectById(projectId: Int): Project
    suspend fun getTasksByProjectId(projectId: Int):ProjectWithTasks
    suspend fun insertTask(task: Task)
}