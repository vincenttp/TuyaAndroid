package com.example.tuyasample.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.tuyasample.presentation.iot.IOTScreen
import com.example.tuyasample.presentation.ui.component.Toolbar
import com.example.tuyasample.presentation.ui.theme.TuyaSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TuyaSampleTheme {
                Scaffold(
                    topBar = { Toolbar() }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        IOTScreen()
                    }
                }
            }
        }
    }
}
