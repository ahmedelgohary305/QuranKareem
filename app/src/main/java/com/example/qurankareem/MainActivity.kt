package com.example.qurankareem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.qurankareem.navigation.NavigationGraph
import com.example.qurankareem.ui.theme.QuranKareemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var switchState by remember {
                mutableStateOf(true)
            }
            QuranKareemTheme(
                darkTheme = switchState
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    NavigationGraph(navController, switchState) {
                        switchState = it
                    }
                }
            }

        }
    }
}

