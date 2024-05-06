package com.example.glowgetter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.glowgetter.ui.ProductListUiState
import com.example.glowgetter.ui.detailpane.EyesCategoryDetailPane
import com.example.glowgetter.ui.detailpane.LipsCategoryDetailPane
import com.example.glowgetter.ui.productlist.LoadingScreen
import com.example.glowgetter.ui.productlist.ProductVideoPlayer
import com.example.glowgetter.ui.theme.GlowGetterTheme
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlowGetterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost()
                }
            }
        }
    }
}