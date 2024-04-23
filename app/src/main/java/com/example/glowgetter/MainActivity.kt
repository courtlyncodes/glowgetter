package com.example.glowgetter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.glowgetter.ui.homescreen.HomePane
import com.example.glowgetter.ui.homescreen.HomePaneMakeupProductsList
import com.example.glowgetter.ui.homescreen.ProductCarousel
import com.example.glowgetter.ui.homescreen.ProductCategories
import com.example.glowgetter.ui.welcomescreen.WelcomeScreen
import com.example.glowgetter.ui.theme.GlowGetterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GlowGetterTheme {
                HomePane()
            }
        }
    }
}