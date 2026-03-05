package com.example.todoandroid

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoandroid.ui.screens.AddTaskScreen
import com.example.todoandroid.ui.screens.ListScreen
import com.example.todoandroid.ui.theme.ToDoAndroidTheme
import java.util.UUID
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.todoandroid.model.Task
import com.example.todoandroid.viewmodel.TaskViewModel
import com.example.todoandroid.data.TaskStorage
import androidx.compose.ui.platform.LocalContext


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAndroidTheme {
                val navController = rememberNavController()
                var tasks by remember {
                    mutableStateOf(
                        listOf(
                            Task(id = "1", text = "Buy milk", date = "2026-03-01"),
                            Task(id = "2", text = "Finish Android assignment", date = "2026-03-01"),
                            Task(id = "3", text = "Ride motorcycle", done = true)
                        )
                    )
                }
                val context = LocalContext.current
                val taskStorage = remember { TaskStorage(context) }
                val viewModel = remember { TaskViewModel(taskStorage) }
                NavHost(
                    navController =navController,
                    startDestination = "list"
                ) {
                    composable("list") {

                        ListScreen(
                            tasks = viewModel.tasks,
                            onCreateClick = {
                                navController.navigate("add")
                            },
                            modifier = Modifier,
                            onToggleDone = { taskId, checked ->
                                viewModel.toggleDone(taskId, checked)
                                           },
                            onSaveEdit = { taskId, newText ->
                                viewModel.editTask(taskId, newText)
                            },
                            onDelete = { taskId ->
                                viewModel.deleteTask(taskId)
                            }
                        )
                    }
                    composable("add") {
                        AddTaskScreen(
                            onSave = { taskText, dateText ->
                                viewModel.addTask(taskText, dateText)
                                navController.popBackStack()
                            },
                            onCancel = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
