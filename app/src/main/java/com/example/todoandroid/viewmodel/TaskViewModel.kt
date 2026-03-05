package com.example.todoandroid.data

import android.content.Context
import com.example.todoandroid.model.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TaskStorage(context: Context) {
    private val prefs = context.getSharedPreferences("tasks_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val key = "tasks_json"

    fun saveTasks(tasks: List<Task>) {
        val json = gson.toJson(tasks)
        prefs.edit().putString(key, json).apply()
    }

    fun loadTasks(): List<Task> {
        val json = prefs.getString(key, null) ?: return emptyList()
        val type = object : TypeToken<List<Task>>() {}.type
        return runCatching { gson.fromJson<List<Task>>(json, type) }.getOrDefault(emptyList())
    }
}


