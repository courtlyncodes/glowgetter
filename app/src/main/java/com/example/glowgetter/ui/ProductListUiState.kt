package com.example.glowgetter.ui

import com.example.glowgetter.Product

sealed interface ProductListUiState {
    data class Success(val products: List<Product>) : ProductListUiState
    object Error : ProductListUiState
    object Loading : ProductListUiState
}