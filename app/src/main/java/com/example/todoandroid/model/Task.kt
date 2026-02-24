package com.example.todoandroid.model

data class Task (
    val id: String,
    val text: String,
    val date: String? = null,
    val done: Boolean = false
)