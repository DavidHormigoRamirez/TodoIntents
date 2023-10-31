package com.alanturing.cpifp.todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.alanturing.cpifp.todo.adapter.TasksAdapter
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding
import com.alanturing.cpifp.todo.model.Task
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val repository = TaskLocalRepository.getInstance()
    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        when (it.resultCode) {
            Activity.RESULT_OK -> binding.tasks.adapter = TasksAdapter(repository,::onShareTask)
            Activity.RESULT_CANCELED -> Snackbar.make(this,binding.createTaskButton,"Tarea no creada",Snackbar.LENGTH_SHORT)
                .show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tasks.adapter = TasksAdapter(repository,::onShareTask)
        binding.createTaskButton.setOnClickListener {
            val intent = Intent(this,CreateTodoActivity::class.java)
            getResult.launch(intent)
        }
    }
    private fun onShareTask(task: Task, view: View) {
        //Toast.makeText(view.context,"Pulsado",Toast.LENGTH_SHORT).show()
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT,getString(R.string.share_text,task.title,task.description))
        }
        val shareIntent = Intent.createChooser(intent,null)
        startActivity(shareIntent)

    }
}