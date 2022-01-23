package com.example.roomdatabaselecture.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class ProjectWithTasks(
    @Embedded
    val project: Project,
    @Relation(parentColumn = "id", entityColumn = "projectId", entity = Task::class)
    val tasks: List<Task>

)