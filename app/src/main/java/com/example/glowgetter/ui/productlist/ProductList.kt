package com.example.glowgetter.ui.productlist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.glowgetter.Product
import com.example.glowgetter.ui.ProductListUiState
import com.example.glowgetter.ui.homescreen.ProductItem


@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier, uiState: ProductListUiState
) {
    when (uiState) {
        is ProductListUiState.Loading -> {
            // TODO
        }

        is ProductListUiState.Error -> {
            // TODO
        }

        is ProductListUiState.Success -> {
            MakeupListPane(productList = uiState.products)
        }
    }
}


@Composable
fun MakeupListPane(
    productList: List<Product>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(157.dp),
        state = rememberLazyGridState(),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding,
    ) {
        item() {}
        items(items = productList) { product ->
            ProductItem(product = product)
        }
    }
}
