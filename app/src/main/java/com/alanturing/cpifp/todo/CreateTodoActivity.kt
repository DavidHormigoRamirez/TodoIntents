package com.alanturing.cpifp.todo

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityCreateTodoBinding
import com.alanturing.cpifp.todo.model.Task

class CreateTodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTodoBinding
    private val repository = TaskLocalRepository.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCreate.setOnClickListener {
            val task = Task(repository.getNextId(),
                binding.titleInput.text.toString(),
                binding.descriptionInput.text.toString(),
                false)
            repository.add(task)
            setResult(Activity.RESULT_OK)
            finish()
        }
        binding.buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}