package com.example.glowgetter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductColor(
    @SerialName("hex_value") val hexValue: String
)

@Serializable
data class Product (
    val id: Int,
    val brand: String?,
    val name: String,
    @SerialName("image_link")
    val image: String,
    @SerialName("product_link")
    val productLink: String?,
    val description: String?,
    val category: String?,
    @SerialName("product_type")
    val productType: String,
)
