package com.example.todoandroid.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoandroid.model.Task
import com.example.todoandroid.ui.components.AppPrimaryButton
import com.example.todoandroid.ui.components.AppTopBar
import com.example.todoandroid.ui.components.TaskItem
import com.example.todoandroid.ui.theme.*
import com.example.todoandroid.viewmodel.TaskViewModel


@OptIn(ExperimentalMaterial3Api::class)
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
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(
                    top = 12.dp,
                    bottom = 80.dp)
            ){
                items(tasks, key = {it.id}) { task ->

                    TaskItem(
                        task = task,
                        isEditing = (editingId == task.id),
                        onToggleDone = { checked ->
                            viewModel.toggleDone(task.id, checked)
                        },
                        onEditClick = {
                            editingId = task.id
                        },
                        onSaveEdit = { newText ->
                            viewModel.editTask(task.id, newText)
                            editingId = null
                        },
                        onCancelEdit = {
                            editingId = null
                        },
                        onDelete = {
                            viewModel.deleteTask(task.id)
                        }
                    )
                }
            }
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