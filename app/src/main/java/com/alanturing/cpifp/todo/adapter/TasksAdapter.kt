package com.alanturing.cpifp.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.TodoItemBinding
import com.alanturing.cpifp.todo.model.Task

class TasksAdapter(private val repository: TaskLocalRepository,
    private val shareTask:(t:Task,v: View)->Unit,
    private val editTask:(t:Task)->Unit): RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    inner class TaskViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTask(t: Task) {
            binding.title.text = t.title
            binding.description.text = t.description
            binding.isCompleted.isChecked = t.isCompleted
            binding.btnShare.setOnClickListener {
                shareTask(t,it)
            }
            binding.btnEdit.setOnClickListener {
                editTask(t)
            }
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