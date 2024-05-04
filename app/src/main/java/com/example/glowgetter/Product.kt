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
    val price: String?,

//    @SerialName("price_sign")
////    val priceSign: Any? = null,
////
////    val currency: Any? = null,

    @SerialName("image_link")
    val image: String,

//    @SerialName("product_link")
//    val productLink: String,

//    @SerialName("website_link")
//    val websiteLink: String,

    val description: String?,
    val rating: Double?,
    val category: String?,

    @SerialName("product_type")
    val productType: String,

//    @SerialName( "api_featured_image")
//    val apiFeaturedImage: String,

    @SerialName("product_colors")
    val productColors: List<ProductColor>?
)
