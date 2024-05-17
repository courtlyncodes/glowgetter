package com.example.glowgetter.data.favorites

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.glowgetter.data.model.Product

@Database(entities = [Product::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao

    companion object {
        @Volatile
        private var Instance: FavoritesDatabase? = null

        fun getDatabase(context: Context): FavoritesDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    FavoritesDatabase::class.java,
                    "favorites_database"
                )
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}
