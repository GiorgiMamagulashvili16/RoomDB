package com.example.roomdatabaselecture.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "project_table")
data class Project(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String,
    val description: String?,
    val date: Long
) : Parcelable
