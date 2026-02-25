package com.example.todoandroid.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.todoandroid.model.Task
import com.example.todoandroid.ui.theme.*

@Composable
fun TaskItem(
    task: Task,
    onToggleDone: (Boolean) -> Unit,
    isEditing: Boolean,
    onEditClick: () -> Unit,
    onSaveEdit: (String) -> Unit,
    onCancelEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val animatedTextColor: Color by animateColorAsState(
        targetValue = if (task.done) TaskDoneRed else TaskTextNormal,
        label = "taskTextColor"
    )
    var editText by remember { mutableStateOf(task.text) }

    // когда открыли редактирование именно этой задачи — подставим текущий текст
    LaunchedEffect(isEditing, task.text) {
        if (isEditing) editText = task.text
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(1.dp, TaskCardBorder, RoundedCornerShape(16.dp))
            .heightIn(min = 72.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
        containerColor = TaskCardBackground
        )
    ) {
        Row (
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                checked = task.done,
                onCheckedChange = onToggleDone,
                colors = CheckboxDefaults.colors(
                    checkedColor = if (task.done) CheckboxDone else CheckboxNormal,
                    uncheckedColor = CheckboxNormal
                )
            )

            Spacer(Modifier.width(12.dp))

            Column (
                modifier = Modifier.weight(1f)
            ) {
                if (isEditing) {
                    OutlinedTextField(
                        value = editText,
                        onValueChange = { editText = it },
                        maxLines = 2,
                        // singleLine = true,
                        textStyle = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = TaskTextNormal
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    Text(
                        text = task.text,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = animatedTextColor,
                        textDecoration = if (task.done) TextDecoration.LineThrough else TextDecoration.None
                    )
                }
                    task.date?.let { d ->
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = d,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
            }
            if (isEditing) {
                IconButton(
                    onClick = { onSaveEdit(editText) },
                    enabled = editText.isNotBlank()
                ) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Save",
                        tint = animatedTextColor
                    )
                }
                IconButton(onClick = onCancelEdit) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Cancel",
                        tint = TaskDoneRed
                    )
                }
            }else{
                if (!task.done) {
                    IconButton(onClick = onEditClick) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit",
                            tint = animatedTextColor
                        )
                    }
                }
                IconButton(onClick = onDelete) {
                    val deleteIconColor = if (task.done)
                        TaskDoneRed
                    else
                        TopBarText
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete Task",
                        tint = deleteIconColor
                    )
                }
            }
        }
    }
}