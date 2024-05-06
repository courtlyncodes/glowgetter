package com.example.glowgetter.ui.productlist

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.glowgetter.Product
import com.example.glowgetter.ui.ProductUiState
import com.example.glowgetter.ui.homepane.ProductItem

@Composable
fun FavoritesScreen(
    favoritesList: List<Product>,
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    favoritesUiState: ProductUiState,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier
    ) {
        items(favoritesList.size){
            ProductItem(
                product = favoritesList[it],
                onProductClick = onProductClick,
                onFavoritesClick = onFavoritesClick,
                favoritesUiState = favoritesUiState
            )
        }

    }
}
