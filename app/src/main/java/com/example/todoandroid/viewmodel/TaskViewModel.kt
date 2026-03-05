package com.example.todoandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.todoandroid.data.TaskStorage
import com.example.todoandroid.model.Task
import java.util.UUID

class TaskViewModel(
    private val storage: TaskStorage
) : ViewModel() {

    var tasks by mutableStateOf(storage.loadTasks())
        private set

    init {
        if (tasks.isEmpty()) {
            tasks = listOf(
                Task(id = "1", text = "Buy milk", date = "2026-03-01"),
                Task(id = "2", text = "Finish Android assignment", date = "2026-03-01"),
                Task(id = "3", text = "Ride motorcycle", done = true)
            )
            storage.saveTasks(tasks)
        }
    }

    fun addTask(text: String, date: String?) {
        val newTask = Task(
            id = UUID.randomUUID().toString(),
            text = text.trim(),
            date = date?.takeIf { it.isNotBlank() }
        )
        tasks = tasks + newTask
        storage.saveTasks(tasks)
    }

    fun toggleDone(taskId: String, checked: Boolean) {
        tasks = tasks.map { if (it.id == taskId) it.copy(done = checked) else it }
        storage.saveTasks(tasks)
    }

    fun editTask(taskId: String, newText: String) {
        tasks = tasks.map { if (it.id == taskId) it.copy(text = newText.trim()) else it }
        storage.saveTasks(tasks)
    }

    fun deleteTask(taskId: String) {
        tasks = tasks.filter { it.id != taskId }
        storage.saveTasks(tasks)
    }
}