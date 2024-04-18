package com.example.glowgetter.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.glowgetter.R

enum class AppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
){
    HOME(label = R.string.home, icon = Icons.Filled.Home, contentDescription = R.string.home),
    CATEGORIES(label = R.string.categories, icon = Icons.Filled.Info, contentDescription = R.string.categories),
    FAVORITES(label = R.string.favorites, icon = Icons.Filled.Favorite, contentDescription = R.string.favorites),
    GUIDES(label = R.string.guides, icon = Icons.Filled.Face, contentDescription = R.string.guides),
}
@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
@Composable
fun GlowGetterApp(
    modifier: Modifier = Modifier
){
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                    Icon(
                        it.icon,
                        contentDescription = stringResource(it.contentDescription)
                    )
                },
                label = {
                    Text(stringResource(it.label))
                },
                selected = currentDestination == it,
                onClick = { currentDestination = it }
            )
            }
        }
    ) {

    }
}