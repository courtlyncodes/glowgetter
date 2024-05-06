package com.example.glowgetter.ui

import com.example.glowgetter.Product

data class ProductUiState(
    val favorites: List<Product> = emptyList()
)