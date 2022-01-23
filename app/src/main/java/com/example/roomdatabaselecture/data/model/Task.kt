package com.example.roomdatabaselecture.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int? = null,
    val task: String,
    val projectId:Int
)