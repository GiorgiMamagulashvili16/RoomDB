package com.example.roomdatabaselecture.ui.add_project_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabaselecture.App
import com.example.roomdatabaselecture.data.model.Project
import com.example.roomdatabaselecture.data.repositories.ProjectRepository
import com.example.roomdatabaselecture.data.repositories.ProjectRepositoryImpl
import kotlinx.coroutines.launch

class AddProjectViewModel : ViewModel() {

    private val projectRepository: ProjectRepository by lazy { ProjectRepositoryImpl(App.db.getProjectDao()) }

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val _successLiveData = MutableLiveData<Unit>()
    val successLiveData: LiveData<Unit> = _successLiveData

     fun addProject(title: String, description: String) = viewModelScope.launch {
        if (title.isNotBlank() && description.isNotBlank()) {
            val project =
                Project(title = title, description = description, date = System.currentTimeMillis())
            projectRepository.insertProject(project)
            _successLiveData.postValue(Unit)
        } else {
            _errorLiveData.postValue("please fill all fields")
        }
    }
}