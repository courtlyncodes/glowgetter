package com.example.glowgetter

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.glowgetter.ui.detailpane.HomeAndCategoryScreen
import com.example.glowgetter.ui.productinfo.DetailScreen
import com.example.glowgetter.ui.productinfo.EyesLipsProductListScreen
import com.example.glowgetter.ui.productinfo.FaceProductListScreen
import com.example.glowgetter.ui.productinfo.FavoritesScreen
import com.example.glowgetter.ui.productinfo.Glossary
import com.example.glowgetter.ui.productinfo.Guides
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel
import com.example.glowgetter.ui.welcomescreen.WelcomeScreen
import kotlinx.coroutines.launch

enum class NavGraph {
    APP,
    WELCOME,
    EYES_LIPS,
    FACE,
    DETAIL,
}

enum class AppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
) {
    HOME(R.string.home, Icons.Filled.Home, R.string.home),
    FAVORITES(R.string.favorites, Icons.Filled.Favorite, R.string.favorites),
    GLOSSARY(R.string.glossary, Icons.Filled.Info, R.string.glossary),
    GUIDES(R.string.guides_and_tutorials, Icons.Filled.Face, R.string.guides_and_tutorials)
}

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
@Composable
fun GlowGetterNavHost(
    viewModel: GlowGetterViewModel = viewModel(factory = GlowGetterViewModel.Factory),
    navController: NavHostController = rememberNavController(),
) {
    val startDestination: String = NavGraph.WELCOME.name
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    val coroutineScope = rememberCoroutineScope()
    val favoritesList by viewModel.favoritesList.collectAsState()
    val username by viewModel.username.collectAsState()


    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavGraph.WELCOME.name) {
            WelcomeScreen(
                username = username,
                onValueChange = {
                    viewModel.setUsername(it)
                },
                onWelcomeClick = {
                    if(username.isNotEmpty())
                    navController.navigate(NavGraph.APP.name)
                },
                onContinueClick = {
                    navController.navigate(NavGraph.APP.name)
                }
            )
        }
        composable(NavGraph.EYES_LIPS.name) {
            EyesLipsProductListScreen(
                videoId = viewModel.videoId,
                lifecycleOwner = LocalLifecycleOwner.current,
                productName = viewModel.productName,
                uiState = viewModel.productListUiState,
                onProductClick = {
                    navController.navigate(NavGraph.DETAIL.name)
                },
                onFavoritesClick = {
                    coroutineScope.launch {
                        if (viewModel.favoritesUiState.favorites.contains(it)) {
                            viewModel.removeProductFromFavorites(it)

                        } else
                            viewModel.addProductToFavorites(it)
                    }
                    viewModel.updateFavoritesUiState(favoritesList.favorites)
                }
            )
        }
        composable(NavGraph.FACE.name) {
            FaceProductListScreen(
                videoId = viewModel.videoId,
                lifecycleOwner = LocalLifecycleOwner.current,
                productName = viewModel.productName,
                onFirstFoundationClick = {
                    viewModel.onTypeQueryChanged("foundation", "liquid")
                },
                onSecondFoundationClick = {
                    viewModel.onTypeQueryChanged("foundation", "bb_cc")
                    viewModel.updateProductName("BB + CC Cream")
                },
                onThirdFoundationClick = {
                    viewModel.onTypeQueryChanged("foundation", "mineral")
                    viewModel.updateProductName("Mineral Foundation")
                },
                onFourthFoundationClick = {
                    viewModel.onTypeQueryChanged("foundation", "powder")
                    viewModel.updateProductName("Powder Foundation")
                },
                onFirstBlushClick = {
                    viewModel.onTypeQueryChanged("blush", "powder")
                    viewModel.updateProductName("Powder Blush")
                },
                onSecondBlushClick = {
                    viewModel.onTypeQueryChanged("blush", "cream")
                    viewModel.updateProductName("Cream Blush")
                },
                onProductClick = { product ->
                    viewModel.updateProduct(product)
                    navController.navigate(NavGraph.DETAIL.name)
                },
                onFavoritesClick = {
                    coroutineScope.launch {
                        if (viewModel.favoritesUiState.favorites.contains(it)) {
                            viewModel.removeProductFromFavorites(it)
                        } else
                            viewModel.addProductToFavorites(it)
                    }
                    viewModel.updateFavoritesUiState(favoritesList.favorites)
                },
                viewModel = viewModel,
                uiState = viewModel.productListUiState
            )
        }
        composable(NavGraph.DETAIL.name) {
            viewModel.product?.let { product ->
                DetailScreen(
                    product = product,
                    onFavoritesClick = {
                        coroutineScope.launch {
                            if (viewModel.favoritesUiState.favorites.contains(it)) {
                                viewModel.removeProductFromFavorites(it)
                            } else
                                viewModel.addProductToFavorites(it)
                        }
                        viewModel.updateFavoritesUiState(favoritesList.favorites)
                    }
                )
            }
        }
        composable(NavGraph.APP.name) {
            NavigationSuiteScaffold(
                navigationSuiteItems = {
                    AppDestinations.entries.map {
                        item(
                            icon = {
                                Icon(
                                    it.icon,
                                    contentDescription = stringResource(it.contentDescription)
                                )
                            },
                            label = { Text(stringResource(it.label)) },
                            selected = it == currentDestination,
                            onClick = { currentDestination = it }
                        )
                    }
                }
            )
            {
                when (currentDestination) {
                    AppDestinations.HOME -> {
                        Log.wtf("username home", username)
                        HomeAndCategoryScreen(
                            username = if(username.isEmpty()) "Welcome" else "Hey, $username!",
                            onFirstCardClick =
                            {
                                viewModel.onTypeQueryChanged("eyebrow", null)
                                viewModel.updateVideoId("7msz_v3FcOY")
                                viewModel.updateProductName("Eyebrow Pencils")
                                navController.navigate(NavGraph.EYES_LIPS.name)
                            },
                            onSecondCardClick = {
                                viewModel.onTypeQueryChanged("mascara", "")
                                viewModel.updateVideoId("20DGbBeZo4E")
                                viewModel.updateProductName("Mascara")
                                navController.navigate(NavGraph.EYES_LIPS.name)
                            },
                            onThirdCardClick = {
                                viewModel.onTypeQueryChanged("eyeliner", null)
                                viewModel.updateVideoId("xJyx2VZY-Is")
                                viewModel.updateProductName("Eyeliner")
                                navController.navigate(NavGraph.EYES_LIPS.name)
                            },
                            onFourthCardClick = {
                                viewModel.onTypeQueryChanged("eyeshadow", "palette")
                                viewModel.updateVideoId("bbUy7QOE-38")
                                viewModel.updateProductName("Eyeshadow Palettes")
                                navController.navigate(NavGraph.EYES_LIPS.name)
                            },
                            onFirstFaceCardClick = {
                                viewModel.onTypeQueryChanged("foundation", "liquid")
                                viewModel.updateProductName("Liquid Foundation")
                                viewModel.updateVideoId("c__JPlF5Q7o")
                                navController.navigate(NavGraph.FACE.name)
                            },
                            onSecondFaceCardClick = {
                                viewModel.onTypeQueryChanged("blush", null)
                                viewModel.updateVideoId("1LBnsgHNAkE")
                                viewModel.updateProductName("Blush")
                                navController.navigate(NavGraph.FACE.name)
                            },
                            onThirdFaceCardClick = {
                                viewModel.onTypeQueryChanged("foundation", "concealer")
                                viewModel.updateVideoId("KvqefTqf2JM")
                                viewModel.updateProductName("Concealer")
                                navController.navigate(NavGraph.FACE.name)
                            },
                            onFourthFaceCardClick = {
                                viewModel.onTypeQueryChanged("foundation", "concealer")
                                viewModel.updateVideoId("KvqefTqf2JM")
                                viewModel.updateProductName("Concealer")
                                navController.navigate(NavGraph.FACE.name)
                            },
                            onFifthFaceCardClick = {
                                viewModel.onTypeQueryChanged("foundation", "highlighter")
                                viewModel.updateVideoId("4TNY25txbHI")
                                viewModel.updateProductName("Highlighter")
                                navController.navigate(NavGraph.FACE.name)
                            },
                            onFirstLipsCardClick = {
                                viewModel.onTypeQueryChanged("lipstick", "lip_gloss")
                                viewModel.updateVideoId("bmygzxaV7Hc")
                                viewModel.updateProductName("Lip Gloss")
                                navController.navigate(NavGraph.EYES_LIPS.name)
                            },
                            onSecondLipsCardClick = {
                                viewModel.onTypeQueryChanged("lip_liner", null)
                                viewModel.updateVideoId("bmygzxaV7Hc")
                                viewModel.updateProductName("Lip Liner")
                                navController.navigate(NavGraph.EYES_LIPS.name)
                            },
                            onThirdLipsCardClick = {
                                viewModel.onTypeQueryChanged("lipstick", null)
                                viewModel.updateVideoId("WuNTgwaVwZI")
                                viewModel.updateProductName("Lipstick")
                                navController.navigate(NavGraph.EYES_LIPS.name)
                            },
                            onProductClick = {
                                viewModel.updateProduct(it)
                                navController.navigate(NavGraph.DETAIL.name)
                            },
                            onFavoritesClick = {
                                coroutineScope.launch {
                                    if (viewModel.favoritesUiState.favorites.contains(it)) {
                                        viewModel.removeProductFromFavorites(it)
                                    } else
                                        viewModel.addProductToFavorites(it)
                                }
                                viewModel.updateFavoritesUiState(favoritesList.favorites)
                            }
                        )
                    }
                    AppDestinations.FAVORITES -> {
                        FavoritesScreen(
                            favoritesList = favoritesList.favorites,
                            onProductClick = {
                                viewModel.updateProduct(it)
                                navController.navigate(NavGraph.DETAIL.name)
                            },
                            onFavoritesClick = {
                                coroutineScope.launch {
                                    if (viewModel.favoritesUiState.favorites.contains(it)) {
                                        viewModel.removeProductFromFavorites(it)
                                    } else
                                        viewModel.addProductToFavorites(it)
                                }
                                viewModel.updateFavoritesUiState(favoritesList.favorites)
                            },
                            onBackClick = { navController.navigate(AppDestinations.HOME.name) }
                        )
                    }
                    AppDestinations.GLOSSARY -> {
                        Glossary(
                            onBackClick = { navController.navigate(NavGraph.APP.name) })
                    }
                    AppDestinations.GUIDES -> {
                        Guides(
                            onBackClick = { navController.navigate(NavGraph.APP.name) }
                        )
                    }

                }
            }
        }
    }
}

