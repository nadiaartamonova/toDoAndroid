package com.example.todoandroid.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.todoandroid.ui.components.AppPrimaryButton
import com.example.todoandroid.ui.components.AppTopBar
import com.example.todoandroid.ui.theme.ScreenBackground
import com.example.todoandroid.ui.theme.SecondButtonBackground
import com.example.todoandroid.ui.theme.TaskCardBackground
import com.example.todoandroid.ui.theme.TaskCardBorder
import com.example.todoandroid.ui.theme.TaskTextNormal
import com.example.todoandroid.ui.theme.TopBarBackground
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    onSave: (String, String?) -> Unit,
    onCancel: () -> Unit
) {
    var taskText by remember { mutableStateOf("") }
    var dateText by remember { mutableStateOf("") }
    var dateError by remember { mutableStateOf<String?>(null) }
    val canSave = taskText.isNotBlank() && (dateText.isBlank() || dateError == null)
    Scaffold (
        topBar = {
            AppTopBar(
                title = "Create Task",
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        containerColor = ScreenBackground,
        modifier = Modifier
    ){ padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            OutlinedTextField(
                value = taskText,
                onValueChange = { taskText = it},
                label = {
                    Text(
                        "Task title",
                        style = MaterialTheme.typography.titleLarge.copy(color = TaskTextNormal),
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                textStyle = MaterialTheme.typography.titleLarge.copy(
                    color = TaskTextNormal
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = TaskCardBorder,
                    unfocusedBorderColor = TaskCardBorder,
                    focusedContainerColor = TaskCardBackground,
                    unfocusedContainerColor = TaskCardBackground,
                    cursorColor = TaskTextNormal
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = dateText,
                onValueChange = { input ->
                    val formatted = formatDateInputDdMmYyyy(input)
                    dateText = formatted

                    dateError = when {
                        formatted.isBlank() -> null
                        formatted.length < 10 -> "Format: DD.MM.YYYY"
                        isValidDdMmYyyy(formatted) -> null
                        !isNotPastDdMmYyyy(formatted) -> "Date can’t be in the past"
                        else -> "Invalid date"
                    }
                },
                label = {
                    Text(
                    "Date (optional)",
                        style = MaterialTheme.typography.titleLarge.copy(color = TaskTextNormal),
                    )
                },
                placeholder = { Text("DD.MM.YYYY") },
                singleLine = true,
                isError = dateError != null,
                supportingText = {
                    if (dateError != null) {
                        Text(
                            text = dateError!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(16.dp),
                textStyle = MaterialTheme.typography.titleLarge.copy(
                    color = TaskTextNormal
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = TaskCardBorder,
                    unfocusedBorderColor = TaskCardBorder,
                    focusedContainerColor = TaskCardBackground,
                    unfocusedContainerColor = TaskCardBackground,
                    cursorColor = TaskTextNormal
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
            ) {
                AppPrimaryButton(
                    text = "Save",
                    onClick = { onSave(taskText, dateText) },
                    enabled = canSave,
                    modifier = Modifier.weight(1f)
                )
                AppPrimaryButton(
                    onClick = onCancel,
                    text = "Cancel",
                    contentColor = TopBarBackground,
                    containerColor = SecondButtonBackground,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

private fun formatDateInputDdMmYyyy(raw: String): String {
    val digits = raw.filter { it.isDigit() }.take(8) // DDMMYYYY
    val sb = StringBuilder()
    for (i in digits.indices) {
        sb.append(digits[i])
        if (i == 1 || i == 3) sb.append('.') // DD. MM.
    }
    return sb.toString()
}

private fun isValidDdMmYyyy(date: String): Boolean {
    if (!Regex("""\d{2}\.\d{2}\.\d{4}""").matches(date)) return false
    val (dd, mm, yyyy) = date.split(".").map { it.toInt() }
    if (mm !in 1..12) return false
    val maxDay = when (mm) {
        1,3,5,7,8,10,12 -> 31
        4,6,9,11 -> 30
        2 -> if (isLeapYear(yyyy)) 29 else 28
        else -> return false
    }
    return dd in 1..maxDay
}

private fun isLeapYear(y: Int): Boolean =
    (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)

@RequiresApi(Build.VERSION_CODES.O)
private fun isNotPastDdMmYyyy(date: String): Boolean {
    // date already validated as DD.MM.YYYY
    val (dd, mm, yyyy) = date.split(".").map { it.toInt() }
    val selected = LocalDate.of(yyyy, mm, dd)
    val today = LocalDate.now()
    return !selected.isBefore(today) // today or future
}