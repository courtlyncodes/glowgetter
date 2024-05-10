package com.example.glowgetter.ui.productinfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.glowgetter.Product
import com.example.glowgetter.R
import com.example.glowgetter.ui.FavoritesUiState
import com.example.glowgetter.ui.homepane.ProductItem

@Composable
fun FavoritesScreen(
    favoritesList: List<Product>,
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    favoritesUiState: FavoritesUiState,
    modifier: Modifier = Modifier
) {
    Column(){
        Text(
            text = stringResource(R.string.favorites),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier
    ) {
        items(favoritesList.size) {
            ProductItem(
                product = favoritesList[it],
                onProductClick = onProductClick,
                onFavoritesClick = onFavoritesClick,
                favoritesUiState = favoritesUiState
            )
        }
    }
    }
}
