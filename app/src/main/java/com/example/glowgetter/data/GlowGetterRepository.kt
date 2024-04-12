package com.example.glowgetter.data

import com.example.glowgetter.Product
import com.example.glowgetter.network.ApiService

interface GlowGetterRepository {
    suspend fun getProductsByBrand(brand: String): List<Product>?
    suspend fun getProductsByType(type: String): List<Product>?
}

class DefaultGlowGetterRepository(private val apiService: ApiService) : GlowGetterRepository {
    override suspend fun getProductsByBrand(brand: String): List<Product>? {
        return try {
            val res = apiService.getProductsByBrand(brand)
            if (res.isSuccessful) {
                res.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    override suspend fun getProductsByType(type: String): List<Product>? {
        return try {
            val res = apiService.getProductsByType(type)
            if (res.isSuccessful) {
                res.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}