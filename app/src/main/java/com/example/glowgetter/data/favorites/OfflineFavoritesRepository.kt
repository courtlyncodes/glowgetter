package com.example.glowgetter.data.favorites

import com.example.glowgetter.data.Product
import kotlinx.coroutines.flow.Flow

class OfflineFavoritesRepository(private val favoritesDao: FavoritesDao): FavoritesRepository {
    override fun getFavorites(): Flow<List<Product>> = favoritesDao.getAllFavorites()

    override suspend fun addFavorite(product: Product) = favoritesDao.addFavorite(product)

    override suspend fun removeFavorite(product: Product) = favoritesDao.removeFavorite(product)
}