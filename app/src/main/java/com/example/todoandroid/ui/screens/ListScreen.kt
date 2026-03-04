package com.example.todoandroid.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todoandroid.model.Task
import com.example.todoandroid.ui.components.AppPrimaryButton
import com.example.todoandroid.ui.components.AppTopBar
import com.example.todoandroid.ui.components.TaskItem
import com.example.todoandroid.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    modifier: Modifier,
    tasks: List<Task>,
    onCreateClick: () -> Unit,
    onToggleDone: (taskId: String, checked: Boolean) -> Unit,
    onSaveEdit: (taskId: String, newText: String) -> Unit,
    onDelete: (taskId: String) -> Unit
) {
    var editingId by remember { mutableStateOf<String?>(null) }

    Scaffold (
        topBar = {
            AppTopBar(title = "Mini Planner")
        },
        containerColor = ScreenBackground,
        modifier = Modifier
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(vertical = 12.dp)
            ){
                items(tasks, key = {it.id}) { task ->
                    TaskItem(
                        task = task,
                        isEditing = (editingId == task.id),
                        onToggleDone = { checked ->
                            onToggleDone(task.id, checked)
                        },
                        onEditClick = {
                            editingId = task.id
                        },
                        onSaveEdit = { newText ->
                            onSaveEdit(task.id, newText)
                            editingId = null
                        },
                        onCancelEdit = {
                            editingId = null
                        },
                        onDelete = {
                            onDelete(task.id)
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