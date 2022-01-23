package com.example.roomdatabaselecture.ui.projects_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaselecture.data.model.Project
import com.example.roomdatabaselecture.databinding.ProjectItemBinding

class ProjectAdapter(private val onProjectItemClick: OnProjectItemClick) :
    ListAdapter<Project, ProjectAdapter.ProjectVH>(DiffUtilComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectVH {
        return ProjectVH(
            ProjectItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProjectVH, position: Int) {
        holder.onBind(getItem(position), onProjectItemClick)
    }

    class ProjectVH(private val binding: ProjectItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(project: Project, onProjectItemClick: OnProjectItemClick) {
            with(binding) {
                titleTextView.text = project.title
                descriptionTextView.text = project.description
                root.setOnClickListener {
                    project.id?.let { id -> onProjectItemClick.onProjectClick(id) }
                }
            }
        }
    }
}