package com.alanturing.cpifp.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alanturing.cpifp.todo.adapter.TasksAdapter
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val repository = TaskLocalRepository.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tasks.adapter = TasksAdapter(repository)
        binding.createTaskButton.setOnClickListener {
            val intent = Intent(this,CreateTodoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.tasks.adapter = TasksAdapter(repository)
    }
}