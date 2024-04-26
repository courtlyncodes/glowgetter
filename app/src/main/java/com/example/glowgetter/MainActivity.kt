package com.example.glowgetter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.glowgetter.ui.ProductListUiState
import com.example.glowgetter.ui.homescreen.HomeScreen
import com.example.glowgetter.ui.productlist.DetailPane
import com.example.glowgetter.ui.productlist.ListDetailScreen
import com.example.glowgetter.ui.theme.GlowGetterTheme
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel
import com.example.glowgetter.ui.welcomescreen.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlowGetterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val glowGetterViewModel: GlowGetterViewModel = viewModel(factory = GlowGetterViewModel.Factory)
                  WelcomeScreen()
                }
            }
        }
    }
}