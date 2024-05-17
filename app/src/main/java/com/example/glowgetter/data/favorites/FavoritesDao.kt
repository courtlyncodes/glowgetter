package com.example.glowgetter.data.favorites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.glowgetter.data.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM products")
    fun getAllFavorites(): Flow<List<Product>>

    @Delete
    fun removeFavorite(favorite: Product)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavorite(favorite: Product)
}