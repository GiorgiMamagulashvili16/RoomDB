package com.example.roomdatabaselecture.data.repositories

import com.example.roomdatabaselecture.data.model.Project

interface ProjectRepository {
    suspend fun insertProject(project: Project)
    suspend fun getAllProject(): List<Project>
}