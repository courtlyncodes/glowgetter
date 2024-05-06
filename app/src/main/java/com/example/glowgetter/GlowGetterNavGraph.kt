package com.example.glowgetter

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.glowgetter.ui.detailpane.HomeAndCategoryScreen
import com.example.glowgetter.ui.productlist.DetailScreen
import com.example.glowgetter.ui.productlist.EyesLipsProductListScreen
import com.example.glowgetter.ui.productlist.FaceProductListScreen
import com.example.glowgetter.ui.productlist.FavoritesScreen
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel
import com.example.glowgetter.ui.welcomescreen.WelcomeScreen
import kotlinx.coroutines.launch

enum class NavGraph {
    WELCOME,
    HOME,
    EYES_LIPS,
    FACE,
    DETAIL,
    FAVORITES
}

enum class AppDestinations {
    HOME,
    FAVORITES,
    STORE,
    GUIDES
}

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
@Composable
fun NavHost(
    viewModel: GlowGetterViewModel = viewModel(factory = GlowGetterViewModel.Factory),
    navController: NavHostController = rememberNavController(),
) {
    val startDestination: String = NavGraph.HOME.name
    val currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    val favoritesUiState by viewModel.productUiState.collectAsState()

    NavigationSuiteScaffold(
        navigationSuiteItems = {
                item(
                    icon = {
                        Icon(Icons.Filled.Home,
                                contentDescription = stringResource(R.string.home))
                    },
                    label = { Text(stringResource(R.string.home)) },
                    selected = currentDestination == AppDestinations.HOME,
                    onClick = {
                        navController.navigate(NavGraph.HOME.name)
                    }
                )
                item(
                    icon = {
                        Icon(Icons.Filled.Favorite,
                            contentDescription = stringResource(R.string.favorites))
                    },
                    label = { Text(stringResource(R.string.favorites)) },
                    selected = currentDestination == AppDestinations.FAVORITES,
                    onClick = {
                        navController.navigate(NavGraph.FAVORITES.name)
                    }
                )
                item(
                    icon = {
                        Icon(Icons.Filled.LocationOn,
                            contentDescription = stringResource(R.string.store_locator))
                    },
                    label = { Text(stringResource(R.string.store_locator)) },
                    selected = currentDestination == AppDestinations.STORE,
                    onClick = {
                        navController.navigate(NavGraph.EYES_LIPS.name)
                    }
                )
                item(
                    icon = {
                        Icon(Icons.Filled.Face,
                            contentDescription = stringResource(R.string.guides_and_tutorials))
                    },
                    label = { Text(stringResource(R.string.guides_and_tutorials)) },
                    selected = currentDestination == AppDestinations.GUIDES,
                    onClick = {
                        navController.navigate(NavGraph.DETAIL.name)
                    }
                )
            }
    )
    {
        Scaffold { it ->
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
                            viewModel.updateFavoritesList(it)
                        },
                        favoritesUiState = favoritesUiState
                    )

                }
                composable(NavGraph.EYES_LIPS.name) {
                    EyesLipsProductListScreen(
                        videoId = viewModel.videoId,
                        lifecycleOwner = LocalLifecycleOwner.current,
                        productName = viewModel.productName,
                        uiState = viewModel.productListUiState,
                        onProductClick = { product ->
                            viewModel.updateProduct(product)
                            navController.navigate(NavGraph.DETAIL.name)
                        },
                        onFavoritesClick = {
                            viewModel.updateFavoritesList(it)
                        },
                        favoritesUiState = favoritesUiState
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
                            viewModel.updateFavoritesList(it)
                        },
                        favoritesUiState = favoritesUiState,
                        viewModel = viewModel,
                        uiState = viewModel.productListUiState
                    )
                }
                composable(NavGraph.DETAIL.name) {
                    viewModel.product?.let { product ->
                        DetailScreen(
                            product = product
                        )
                    }
                }
                composable(NavGraph.FAVORITES.name){
                    FavoritesScreen(
                        favoritesList = favoritesUiState.favorites,
                        onProductClick = {
                            viewModel.updateProduct(it)
                            navController.navigate(NavGraph.DETAIL.name)
                        },
                        onFavoritesClick = {
                            viewModel.updateFavoritesList(it)
                        },
                        favoritesUiState = favoritesUiState
                    )
                }

            }
        }
    }
}