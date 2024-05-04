package com.example.glowgetter

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.glowgetter.ui.detailpane.HomeAndCategoryScreen
import com.example.glowgetter.ui.productlist.EyesProductListScreen
import com.example.glowgetter.ui.productlist.FaceProductListScreen
import com.example.glowgetter.ui.productlist.LipsProductListScreen
import com.example.glowgetter.ui.productlist.ProductScreen
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel
import com.example.glowgetter.ui.welcomescreen.WelcomeScreen
import kotlinx.coroutines.launch

enum class NavGraph {
    WELCOME,
    HOME,
    EYES,
    FACE,
    LIPS
}

enum class EyesSubCategory {
    EYEBROW_PENCILS,
    MASCARA,
    EYELINER,
    EYESHADOW
}

enum class FaceSubCategory {
    FOUNDATION,
    BLUSH,
    BRONZER,
    CONCEALER,
    CONTOUR,
    HIGHLIGHTER
}

enum class LipsSubCategory {
    LIP_GLOSS,
    LIP_LINER,
    LIPSTICK
}

@Composable
fun NavHost(
    viewModel: GlowGetterViewModel = viewModel(factory = GlowGetterViewModel.Factory),
    navController: NavHostController = rememberNavController(),
) {
    val startDestination: String = NavGraph.HOME.name
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
                HomeAndCategoryScreen(
                    onFirstCardClick =
                    {
                        viewModel.onTypeQueryChanged("eyebrow", null)
                        viewModel.updateVideoId("7msz_v3FcOY")
                        navController.navigate(NavGraph.EYES.name)
                    },
                    onSecondCardClick = {
                        viewModel.onTypeQueryChanged("mascara", "")
                        viewModel.updateVideoId("6w7kpxmXa_E&t=1s")
                        navController.navigate(NavGraph.EYES.name)
                    },
                    onThirdCardClick = {
                        viewModel.onTypeQueryChanged("eyeliner", null)
                        viewModel.updateVideoId("xJyx2VZY-Is")
                        navController.navigate(NavGraph.EYES.name)
                    },
                    onFourthCardClick = {
                        viewModel.onTypeQueryChanged("eyeshadow", "palette")
                        viewModel.updateVideoId("bbUy7QOE-38")
                        navController.navigate(NavGraph.EYES.name)
                    },
                    onFirstFaceCardClick = {
                        viewModel.onTypeQueryChanged("foundation", null)
                        viewModel.updateVideoId("c__JPlF5Q7o")
                        navController.navigate(NavGraph.FACE.name)
                    },
                    onSecondFaceCardClick = {
                        viewModel.onTypeQueryChanged("blush", null)
                        viewModel.updateVideoId("1LBnsgHNAkE")
                        navController.navigate(NavGraph.FACE.name)
                    },
                    onThirdFaceCardClick = {
                        viewModel.onTypeQueryChanged("bronzer", null)
                        viewModel.updateVideoId("1LBnsgHNAkE")
                        navController.navigate(NavGraph.FACE.name)
                    },
                    onFourthFaceCardClick = {
                        viewModel.onTypeQueryChanged("foundation", "concealer")
                        viewModel.updateVideoId("KvqefTqf2JM")
                        navController.navigate(NavGraph.FACE.name)
                    },
                    onFifthFaceCardClick = {
                        viewModel.onTypeQueryChanged("foundation", "contour")
                        viewModel.updateVideoId("gkkmHizG2As")
                        navController.navigate(NavGraph.FACE.name)
                    },
                    onSixthFaceCardClick = {
                        viewModel.onTypeQueryChanged("foundation", "highlighter")
                        viewModel.updateVideoId("4TNY25txbHI")
                        navController.navigate(NavGraph.FACE.name)
                    },
                )

            }
            composable(NavGraph.EYES.name) {
                EyesProductListScreen(videoId = viewModel.videoId, lifecycleOwner = LocalLifecycleOwner.current, uiState = viewModel.productListUiState )
            }
            composable(NavGraph.FACE.name) {
                FaceProductListScreen(videoId = viewModel.videoId, lifecycleOwner = LocalLifecycleOwner.current, uiState = viewModel.productListUiState )


            }
            composable(NavGraph.LIPS.name) {

            }
        }
    }
}