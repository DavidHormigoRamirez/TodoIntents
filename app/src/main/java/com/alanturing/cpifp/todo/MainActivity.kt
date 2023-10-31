package com.alanturing.cpifp.todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.alanturing.cpifp.todo.adapter.TasksAdapter
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val repository = TaskLocalRepository.getInstance()
    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        when (it.resultCode) {
            Activity.RESULT_OK -> binding.tasks.adapter = TasksAdapter(repository)
            Activity.RESULT_CANCELED -> Snackbar.make(this,binding.createTaskButton,"Tarea no creada",Snackbar.LENGTH_SHORT)
                .show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tasks.adapter = TasksAdapter(repository)
        binding.createTaskButton.setOnClickListener {
            val intent = Intent(this,CreateTodoActivity::class.java)
            getResult.launch(intent)
        }
    }
}