package com.example.chucknorrisapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.chucknorrisapi.feature.jokes.presentation.JokesRoute
import com.example.chucknorrisapi.ui.theme.ChuckNorrisApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChuckNorrisApiTheme {
                JokesRoute()
            }
        }
    }
}

