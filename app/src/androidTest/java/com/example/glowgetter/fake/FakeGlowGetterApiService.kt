package com.example.glowgetter.fake
import com.example.glowgetter.data.model.Product
import com.example.glowgetter.network.ApiService
import retrofit2.Response
import retrofit2.http.Query

class FakeGlowGetterApiService : ApiService {

        private var responseToReturn: Response<List<Product>> = Response.success(emptyList())

        fun setResponse(response: Response<List<Product>>) {
            this.responseToReturn = response
        }

    override suspend fun getProductsByType(@Query("product_type") type: String, @Query("product_category") category: String): Response<List<Product>> {
        return responseToReturn
    }
}