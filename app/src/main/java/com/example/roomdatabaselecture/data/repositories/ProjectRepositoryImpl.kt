package com.example.roomdatabaselecture.data.repositories

import com.example.roomdatabaselecture.data.db.ProjectsDao
import com.example.roomdatabaselecture.data.model.Project
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProjectRepositoryImpl(private val projectsDao: ProjectsDao) : ProjectRepository {
    override suspend fun insertProject(project: Project) = withContext(Dispatchers.IO) {
        projectsDao.insertProject(project)
    }

    override suspend fun getAllProject(): List<Project> = withContext(Dispatchers.IO) {
        return@withContext projectsDao.getAllProjects()
    }
}