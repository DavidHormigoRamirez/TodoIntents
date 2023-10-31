package com.alanturing.cpifp.todo.data

import com.alanturing.cpifp.todo.model.Task

class TaskLocalRepository() {
    companion object {
        private var _INSTANCE:TaskLocalRepository? = null
        fun getInstance():TaskLocalRepository {
            _INSTANCE = _INSTANCE ?: TaskLocalRepository()
            return _INSTANCE!!
        }
    }
    private val _tasks = mutableListOf<Task>()
    private var taskIdCounter = 0

    val tasks:List<Task>
        get() = _tasks

    fun add(task:Task) {
        _tasks.add(task)
    }
    fun delete(id:Int) {
        TODO("Código eliminar tarea por id")
    }
    fun update(task:Task) {
        TODO("Código actualizar tarea con id==id")
    }

    fun getNextId() = ++taskIdCounter
}
