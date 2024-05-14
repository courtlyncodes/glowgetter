package com.example.glowgetter.ui

import com.example.glowgetter.data.Product

data class FavoritesUiState(
    val favorites: List<Product> = emptyList()
)