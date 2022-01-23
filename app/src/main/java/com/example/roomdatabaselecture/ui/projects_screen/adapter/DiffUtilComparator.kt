package com.example.roomdatabaselecture.ui.projects_screen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.roomdatabaselecture.data.model.Project

object DiffUtilComparator : DiffUtil.ItemCallback<Project>() {
    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem == newItem
    }
}