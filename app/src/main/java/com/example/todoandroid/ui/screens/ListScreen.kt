package com.example.todoandroid.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.todoandroid.model.Task
import com.example.todoandroid.ui.components.TaskItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    modifier: Modifier
) {
    var tasks by remember {
        mutableStateOf(
            listOf(
                Task(id = "1", text = "Buy milk", date = "2026-03-01"),
                Task(id = "2", text = "Finish Android assignment"),
                Task(id = "3", text = "Ride motorcycle", done = true)
            )
        )
    }
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mini Planner")}
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
    ) { padding ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ){
            items(tasks, key = {it.id}) { task ->
                TaskItem(
                    task = task,
                    onToggleDone = { checked ->
                        tasks = tasks.map{
                            if (it.id == task.id) it.copy(done = checked) else it
                        }
                    },
                    onDelete = {
                        tasks = tasks.filter { it.id != task.id }
                    }
                )
                Divider(color = MaterialTheme.colorScheme.outlineVariant)
            }
        }
    }
}