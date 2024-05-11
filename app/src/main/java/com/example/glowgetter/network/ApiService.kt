package com.example.glowgetter.network

import com.example.glowgetter.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Return products by type and its subcategories
    @GET("products.json")
    suspend fun getProductsByType(@Query("product_type") type: String, @Query("product_category") category: String): Response<List<Product>>
}