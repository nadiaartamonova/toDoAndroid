package com.example.todoandroid.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todoandroid.model.Task

@Composable
fun TaskItem(
    task: Task,
    onToggleDone: (Boolean) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = task.done,
            onCheckedChange = onToggleDone
        )

        Spacer(Modifier.width(12.dp))

        Column (
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = task.text,
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = if (task.done) TextDecoration.LineThrough else TextDecoration.None
            )
            task.date?.let { d ->
                Spacer(Modifier.height(2.dp))
                Text(
                    text = d,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        IconButton(onClick = onDelete) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete Task")
        }
    }
}