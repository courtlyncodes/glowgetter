package com.example.glowgetter.data.favorites

import com.example.glowgetter.data.Product
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavorites(): Flow<List<Product>>

    suspend fun removeFavorite(product: Product)

    suspend fun addFavorite(product: Product)
}