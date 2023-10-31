package com.alanturing.cpifp.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.TodoItemBinding
import com.alanturing.cpifp.todo.model.Task

class TasksAdapter(private val repository: TaskLocalRepository): RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    inner class TaskViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTask(t: Task) {
            binding.title.text = t.title
            binding.description.text = t.description
            binding.isCompleted.isChecked = t.isCompleted
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TodoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return TaskViewHolder(binding)
    }

    override fun getItemCount() = repository.tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindTask(repository.tasks[position])
    }
}