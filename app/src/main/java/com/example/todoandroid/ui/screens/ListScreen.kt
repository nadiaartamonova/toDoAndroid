package com.example.todoandroid.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.todoandroid.ui.components.TaskItem
import com.example.todoandroid.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    modifier: Modifier
) {
    var tasks by remember {
        mutableStateOf(
            listOf(
                Task(id = "1", text = "Buy milk", date = "2026-03-01"),
                Task(id = "2", text = "Finish Android assignment", date = "2026-03-01"),
                Task(id = "3", text = "Ride motorcycle", done = true)
            )
        )
    }
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
                title = {
                    Text(
                        "Mini Planner",
                        color = TopBarText,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = TopBarBackground
                )
            )
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
                        onToggleDone = { checked ->
                            tasks = tasks.map{
                                if (it.id == task.id) it.copy(done = checked) else it
                            }
                        },
                        onDelete = {
                            tasks = tasks.filter { it.id != task.id }
                        }
                    )
                }
            }
            Button(
                onClick = {
                    //TODO
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 32.dp
                    )
                    .fillMaxWidth()
                    .height(52.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TopBarBackground,
                    contentColor = TopBarText
                )

            ) {
                Text(
                    "Create new task",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

    }
}