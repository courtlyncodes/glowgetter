package com.example.glowgetter.data

import com.example.glowgetter.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface GlowGetterAppContainer {
    val glowGetterRepository: GlowGetterRepository
}

// Implementation for the Dependency Injection container at the application level.
//  Variables are initialized lazily and the same instance is shared across the whole app.
class DefaultGlowGetterAppContainer : GlowGetterAppContainer {
    private val baseUrl = "https://makeup-api.herokuapp.com/api/v1/"

    private val json = Json {
        ignoreUnknownKeys = true  // Set ignoreUnknownKeys to true
    }

    //Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    // Retrofit service object for creating api calls
    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    // DI implementation for the repository
    override val glowGetterRepository: GlowGetterRepository by lazy {
        DefaultGlowGetterRepository(retrofitService)
    }
}