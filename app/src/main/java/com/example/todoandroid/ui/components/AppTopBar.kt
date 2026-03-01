package com.example.todoandroid.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todoandroid.ui.theme.TopBarBackground
import com.example.todoandroid.ui.theme.TopBarText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.clip(
            RoundedCornerShape(
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            )
        ),
        title = {
            Text(
                text = title,
                color = TopBarText,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium
            ) },
        navigationIcon = { navigationIcon?.invoke() },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = TopBarBackground
        )
    )
}