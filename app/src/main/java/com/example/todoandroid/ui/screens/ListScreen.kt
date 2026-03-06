package com.example.todoandroid.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoandroid.ui.components.AppPrimaryButton
import com.example.todoandroid.ui.components.AppTopBar
import com.example.todoandroid.ui.components.TaskList
import com.example.todoandroid.ui.theme.*
import com.example.todoandroid.viewmodel.TaskViewModel


@Composable
fun ListScreen(
    modifier: Modifier,
    viewModel: TaskViewModel,
    onCreateClick: () -> Unit
) {
    var editingId by remember { mutableStateOf<String?>(null) }
    val tasks = viewModel.tasks


    Scaffold (
        topBar = {
            AppTopBar(title = "Mini Planner")
        },
        containerColor = ScreenBackground,
        modifier = modifier
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            TaskList(
                tasks = tasks,
                editingId = editingId,
                onToggleDone = { task, checked ->
                    viewModel.toggleDone(task.id, checked)
                },
                onEditClick = { task ->
                    editingId = task.id
                },
                onSaveEdit = { task, newText ->
                    viewModel.editTask(task.id, newText)
                    editingId = null
                },
                onCancelEdit = {
                    editingId = null
                },
                onDelete = { task ->
                    viewModel.deleteTask(task.id)
                },
                modifier = Modifier.padding(padding)
            )
            AppPrimaryButton(
                text = "Create new task",
                onClick = onCreateClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
            )
        }

    }
}