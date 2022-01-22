package com.example.roomdatabaselecture.ui.projects_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabaselecture.App
import com.example.roomdatabaselecture.data.model.Project
import com.example.roomdatabaselecture.data.repositories.ProjectRepository
import com.example.roomdatabaselecture.data.repositories.ProjectRepositoryImpl
import kotlinx.coroutines.launch

class ProjectsViewModel : ViewModel() {

    private val projectRepository: ProjectRepository by lazy { ProjectRepositoryImpl(App.db.getProjectDao()) }

    private val _allProjectData = MutableLiveData<List<Project>>()
     val allProjectData: LiveData<List<Project>> = _allProjectData

    fun setAllProject() = viewModelScope.launch {
        _allProjectData.postValue(projectRepository.getAllProject())
    }
}