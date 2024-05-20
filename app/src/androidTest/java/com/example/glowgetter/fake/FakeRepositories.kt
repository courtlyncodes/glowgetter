package com.example.glowgetter.fake

import com.example.glowgetter.data.GlowGetterRepository
import com.example.glowgetter.data.model.Product


class FakeGlowGetterRepository : GlowGetterRepository {
        override suspend fun getProductsByType(type: String, subtype: String): List<Product> {
            return FakeProductsSource.getFakeProductList()
        }
}
