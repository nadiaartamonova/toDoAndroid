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
import androidx.compose.runtime.remember
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
                val context = LocalContext.current
                val taskStorage = remember { TaskStorage(context.applicationContext) }
                val viewModel = remember { TaskViewModel(taskStorage) }

                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        ListScreen(
                            modifier = Modifier,
                            viewModel = viewModel,
                            onCreateClick = { navController.navigate("add") }
                        )
                    }
                    composable("add") {
                        AddTaskScreen(
                            viewModel = viewModel,
                            onCancel = { navController.popBackStack() },
                            onSaved = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
