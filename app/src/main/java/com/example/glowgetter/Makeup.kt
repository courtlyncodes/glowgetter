package com.example.glowgetter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Makeup (
    val id: Int,
    val brand: String,
    val name: String,
    val price: String,

//    @SerialName("price_sign")
////    val priceSign: Any? = null,
////
////    val currency: Any? = null,

    @SerialName("image_link")
    val imageLink: String,

    @SerialName("product_link")
    val productLink: String,

//    @SerialName("website_link")
//    val websiteLink: String,

    val description: String,
    val rating: Long,
    val category: String,

    @SerialName("product_type")
    val productType: String,

    @SerialName( "created_at")
    val createdAt: String,

//    @SerialName( "api_featured_image")
//    val apiFeaturedImage: String,

    @SerialName( "product_colors")
    val productColors: List<String>?
)

