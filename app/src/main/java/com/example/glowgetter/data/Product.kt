package com.example.glowgetter.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "products")
@Serializable
data class Product (
    @PrimaryKey
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
    val productType: String
)
