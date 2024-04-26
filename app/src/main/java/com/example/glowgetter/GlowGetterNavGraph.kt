package com.example.glowgetter

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel
import com.example.glowgetter.ui.welcomescreen.WelcomeScreen
import kotlinx.coroutines.launch

enum class NavGraph {
    WELCOME,
    HOME,
    DETAILS
}

@Composable
fun CahierNavHost(
    viewModel: GlowGetterViewModel = viewModel(factory = GlowGetterViewModel.Factory),
    navController: NavHostController = rememberNavController(),
) {
    val startDestination: String = NavGraph.WELCOME.name
    val coroutineScope = rememberCoroutineScope()


    Scaffold {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(it)
        ) {
            composable(NavGraph.WELCOME.name) {
                WelcomeScreen()
            }
            composable(NavGraph.HOME.name) {

            }
            composable(NavGraph.DETAILS.name) {

            }
        }
    }
}