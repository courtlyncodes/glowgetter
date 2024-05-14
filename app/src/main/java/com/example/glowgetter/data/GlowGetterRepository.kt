package com.example.glowgetter.data

import com.example.glowgetter.network.ApiService

interface GlowGetterRepository {
    suspend fun getProductsByType(type: String, subtype: String): List<Product>?
}

class DefaultGlowGetterRepository(private val apiService: ApiService) : GlowGetterRepository {

    override suspend fun getProductsByType(type: String, subtype: String): List<Product>? {
        return try {
            val res = apiService.getProductsByType(type, subtype)
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