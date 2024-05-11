package com.example.glowgetter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.glowgetter.ui.GlowGetterApp
import com.example.glowgetter.ui.theme.GlowGetterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlowGetterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    GlowGetterApp()
                }
            }
        }
    }
}