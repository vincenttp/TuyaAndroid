package com.example.tuyasample.presentation.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.tuyasample.presentation.ui.theme.Blue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
    TopAppBar(
        title = { Text(text = "IOT") },
        navigationIcon = { /* Optional: Add navigation icon */ },
        actions = {
            // Optional: Add action icons or buttons
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Blue,
            titleContentColor = Color.White
        ),
    )
}