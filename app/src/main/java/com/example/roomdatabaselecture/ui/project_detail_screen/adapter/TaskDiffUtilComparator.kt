package com.example.roomdatabaselecture.ui.project_detail_screen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.roomdatabaselecture.data.model.Task

object TaskDiffUtilComparator : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}