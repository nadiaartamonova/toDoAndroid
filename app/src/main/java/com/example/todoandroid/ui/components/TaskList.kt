package com.example.todoandroid.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoandroid.model.Task
import androidx.compose.foundation.lazy.items

@Composable
fun TaskList(
    tasks: List<Task>,
    editingId: String?,
    onToggleDone: (Task, Boolean) -> Unit,
    onEditClick: (Task) -> Unit,
    onSaveEdit: (Task, String) -> Unit,
    onCancelEdit: () -> Unit,
    onDelete: (Task) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(top = 12.dp, bottom = 80.dp)
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding
    ) {
        items(tasks, key = { it.id }) { task ->
            TaskItem(
                task = task,
                isEditing = editingId == task.id,
                onToggleDone = { checked -> onToggleDone(task, checked) },
                onEditClick = { onEditClick(task) },
                onSaveEdit = { newText -> onSaveEdit(task, newText) },
                onCancelEdit = onCancelEdit,
                onDelete = { onDelete(task) }
            )
        }
    }
}