package com.alanturing.cpifp.todo.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityEditTodoBinding
import com.alanturing.cpifp.todo.model.Task

class EditTodoActivity : AppCompatActivity() {
    companion object {
        const val TASK = "com.alanturing.cpifp.todo.TASK"
    }
    private lateinit var binding: ActivityEditTodoBinding
    private val repository = TaskLocalRepository.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val task:Task? = intent?.extras?.getParcelable(TASK)
        if (task != null) {
            bindTask(task)
        }
        binding.buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        binding.buttonUpdate.setOnClickListener {
            if (task!=null) {
                task.title = binding.titleInput.text.toString()
                task.description = binding.descriptionInput.text.toString()
                task.isCompleted = binding.statusCheckbox.isChecked
                repository.update(task)
                setResult(Activity.RESULT_OK)
                finish()
            }
        }


    }
    private fun bindTask(task:Task) {
        binding.descriptionInput.setText(task.description)
        binding.titleInput.setText(task.title)
        binding.statusCheckbox.isChecked = task.isCompleted
    }
}