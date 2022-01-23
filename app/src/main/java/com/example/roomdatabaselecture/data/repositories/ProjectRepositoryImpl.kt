package com.example.roomdatabaselecture.data.repositories

import com.example.roomdatabaselecture.data.db.ProjectsDao
import com.example.roomdatabaselecture.data.model.Project
import com.example.roomdatabaselecture.data.model.ProjectWithTasks
import com.example.roomdatabaselecture.data.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProjectRepositoryImpl(private val projectsDao: ProjectsDao) : ProjectRepository {
    override suspend fun insertProject(project: Project) = withContext(Dispatchers.IO) {
        projectsDao.insertProject(project)
    }

    override suspend fun getAllProject(): List<Project> = withContext(Dispatchers.IO) {
        return@withContext projectsDao.getAllProjects()
    }

    override suspend fun getProjectById(projectId: Int): Project = withContext(Dispatchers.IO) {
        return@withContext projectsDao.getProjectById(projectId)
    }

    override suspend fun getTasksByProjectId(projectId: Int): ProjectWithTasks {
        return withContext(Dispatchers.IO) {
            projectsDao.getTasksByProjectId(projectId)
        }
    }

    override suspend fun insertTask(task: Task) {
        return withContext(Dispatchers.IO) {
            projectsDao.insertTask(task)
        }
    }
}