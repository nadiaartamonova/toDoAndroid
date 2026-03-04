package com.example.todoandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoandroid.ui.screens.AddTaskScreen
import com.example.todoandroid.ui.screens.ListScreen
import com.example.todoandroid.ui.theme.ToDoAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAndroidTheme {
                val navController = rememberNavController()
                NavHost(
                    navController =navController,
                    startDestination = "list"
                ) {
                    composable("list") {
                        ListScreen(
                            onCreateClick = {
                                navController.navigate("add")
                            },
                            modifier = Modifier
                        )
                    }
                    composable("add") {
                        AddTaskScreen(
                            onSave = { taskText, dateText ->
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
