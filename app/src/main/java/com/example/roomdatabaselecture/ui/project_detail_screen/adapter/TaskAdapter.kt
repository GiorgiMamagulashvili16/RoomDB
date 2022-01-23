package com.example.roomdatabaselecture.ui.project_detail_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaselecture.data.model.Task
import com.example.roomdatabaselecture.databinding.TaskItemBinding

class TaskAdapter : ListAdapter<Task, TaskAdapter.TaskVH>(TaskDiffUtilComparator) {

    class TaskVH(private val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(task: Task) {
            binding.taskTextView.text = task.task
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        return TaskVH(TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        holder.onBind(getItem(position))
    }
}