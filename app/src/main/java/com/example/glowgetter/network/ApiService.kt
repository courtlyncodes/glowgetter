package com.example.glowgetter.network

import com.example.glowgetter.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Return products by brand
    @GET("")
    suspend fun getProductsByBrand(@Query("brand") brand: String): Response<List<Product>>

    // Return products by type
    @GET("products.json")
    suspend fun getProductsByType(@Query("product_type") type: String): Response<List<Product>>
}