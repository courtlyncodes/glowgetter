package com.example.glowgetter.ui

import com.example.glowgetter.Product

data class FavoritesUiState(
    val favorites: List<Product> = emptyList()
)