package com.example.glowgetter.ui.productinfo

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.glowgetter.data.model.Product
import com.example.glowgetter.R
import com.example.glowgetter.ui.homepane.GlowGetterTopAppBar
import com.example.glowgetter.ui.homepane.ProductItem

@Composable
fun FavoritesScreen(
    favoritesList: List<Product>,
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackClick()
    }
    Column {
        GlowGetterTopAppBar(text = stringResource(R.string.favorites))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = modifier
        ) {
            items(favoritesList.size) {
                ProductItem(
                    product = favoritesList[it],
                    onProductClick = onProductClick,
                    onFavoritesClick = onFavoritesClick
                )
            }
        }
    }
}
