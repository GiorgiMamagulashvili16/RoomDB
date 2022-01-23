package com.example.roomdatabaselecture.ui.project_detail_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabaselecture.App
import com.example.roomdatabaselecture.data.model.Project
import com.example.roomdatabaselecture.data.model.Task
import com.example.roomdatabaselecture.data.repositories.ProjectRepository
import com.example.roomdatabaselecture.data.repositories.ProjectRepositoryImpl
import kotlinx.coroutines.launch

class ProjectDetailViewModel : ViewModel() {
    private val projectRepository: ProjectRepository by lazy { ProjectRepositoryImpl(App.db.getProjectDao()) }

    private val _projectIdLiveData = MutableLiveData<Int>()
    val projectIdLiveData: LiveData<Int> = _projectIdLiveData

    private val _projectLiveData = MutableLiveData<Project>()
    val projectLiveData: LiveData<Project> = _projectLiveData

    private val _tasksLiveData = MutableLiveData<List<Task>>()
    val tasksLiveData: LiveData<List<Task>> = _tasksLiveData

    fun setTasks(projectId: Int) = viewModelScope.launch {
        _tasksLiveData.postValue(projectRepository.getTasksByProjectId(projectId).tasks)
    }

    fun setProjectId(projectId: Int) = viewModelScope.launch {
        _projectIdLiveData.postValue(projectId)
    }

    fun setProject(projectId: Int) = viewModelScope.launch {
        _projectLiveData.postValue(projectRepository.getProjectById(projectId))
    }
}