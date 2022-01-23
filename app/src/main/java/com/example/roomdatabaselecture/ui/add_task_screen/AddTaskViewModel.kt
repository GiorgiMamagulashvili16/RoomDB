package com.example.roomdatabaselecture.ui.add_task_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabaselecture.App
import com.example.roomdatabaselecture.data.model.Task
import com.example.roomdatabaselecture.data.repositories.ProjectRepository
import com.example.roomdatabaselecture.data.repositories.ProjectRepositoryImpl
import kotlinx.coroutines.launch

class AddTaskViewModel : ViewModel() {

    private val projectRepository: ProjectRepository by lazy { ProjectRepositoryImpl(App.db.getProjectDao()) }
    private val _projectIdLiveData = MutableLiveData<Int>()
    val projectIdLiveData: LiveData<Int> = _projectIdLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val _successLiveData = MutableLiveData<Unit>()
    val successLiveData: LiveData<Unit> = _successLiveData

    fun setProjectId(projectId: Int) = viewModelScope.launch {
        _projectIdLiveData.postValue(projectId)
    }

    fun insertTask(task: String) = viewModelScope.launch {
        if (task.isNotBlank()) {
            projectIdLiveData.value?.let { projectId ->
                Task(
                    task = task,
                    projectId = projectId
                )
            }?.let {
                projectRepository.insertTask(it)
            }
            _successLiveData.postValue(Unit)

        } else
            _errorLiveData.postValue("please fill field!")
    }
}