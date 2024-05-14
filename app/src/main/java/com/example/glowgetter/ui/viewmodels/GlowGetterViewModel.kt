package com.example.glowgetter.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.glowgetter.data.GlowGetterRepository
import com.example.glowgetter.GlowGetterApplication
import com.example.glowgetter.data.favorites.FavoritesRepository
import com.example.glowgetter.data.Product
import com.example.glowgetter.ui.ProductListUiState
import com.example.glowgetter.ui.FavoritesUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class GlowGetterViewModel(
    private val glowGetterRepository: GlowGetterRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    var productListUiState: ProductListUiState by mutableStateOf(ProductListUiState.Loading)
        private set

//    private val _favoritesUiState = MutableStateFlow(FavoritesUiState())
//    val favoritesUiState: StateFlow<FavoritesUiState> = _favoritesUiState.asStateFlow()

    private var productQuery: String = ""
    var videoId: String = ""

    private var typeQuery: String = ""
    private var subtypeQuery: String = ""

    var productName: String = ""

    var product: Product? = null

    val favoritesList: StateFlow<FavoritesUiState> = favoritesRepository.getFavorites().map { FavoritesUiState(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FavoritesUiState()
    )
    var favoritesUiState by mutableStateOf(FavoritesUiState())
        private set

//    var favoritesList: List<Product> = emptyList()


    init {
        getProductListByType(typeQuery, subtypeQuery)
//        viewModelScope.launch {
//            favoritesList.collect { favorites ->
//                _favoritesUiState.value = favorites
//            }
//        }
    }

    fun onTypeQueryChanged(firstQuery: String, secondQuery: String?) {
        typeQuery = firstQuery
        if (secondQuery != null) {
            subtypeQuery = secondQuery
        }
        getProductListByType(typeQuery, subtypeQuery)
    }

    // Function to check the status of an image URL. If the image URL returns a valid response code, it returns true.
    private suspend fun checkImageStatus(imageUrl: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val connection = java.net.URL(imageUrl).openConnection() as java.net.HttpURLConnection
                connection.requestMethod = "HEAD"
                val statusCode = connection.responseCode
                statusCode == java.net.HttpURLConnection.HTTP_OK
            } catch (e: IOException) {
                // Handle exceptions, such as network errors
                e.printStackTrace()
                false
            }
        }
    }

    private fun getProductListByType(type: String, subtype: String) {
        viewModelScope.launch {
            productListUiState = ProductListUiState.Loading
            productListUiState = try {
                val productList = glowGetterRepository.getProductsByType(type, subtype)

                if (productList == null) {
                    ProductListUiState.Error
                } else {
                    // Filter out products with invalid images
                    val validProducts = productList.filter { product ->
                        checkImageStatus(product.image)
                    }
                    ProductListUiState.Success(validProducts)
                }
            } catch (e: IOException) {
                ProductListUiState.Error
            } catch (e: HttpException) {
                ProductListUiState.Error
            }
        }
    }

//    fun updateFavoritesList(product: Product) {
//        val updatedFavorites = _favoritesUiState.value.favorites.toMutableList()
//        if (updatedFavorites.contains(product)) {
//            updatedFavorites.remove(product)
//        } else {
//            updatedFavorites.add(product)
//        }
//        _favoritesUiState.value = _favoritesUiState.value.copy(favorites = updatedFavorites)
//    }
     fun updateFavoritesUiState(favorites: List<Product>) {
        favoritesUiState = FavoritesUiState(favorites)
    }
    suspend fun addProductToFavorites(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesRepository.addFavorite(product)
        }
    }

    suspend fun removeProductFromFavorites(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesRepository.removeFavorite(product)
        }
    }

    fun updateProductCategory(category: String) {
        productQuery = category
    }

    fun updateVideoId(videoId: String) {
        this.videoId = videoId
    }

    fun updateProductName(productName: String) {
        this.productName = productName
    }

    fun updateProduct(product: Product) {
        this.product = product
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as GlowGetterApplication)
                val glowGetterRepository = application.glowGetterAppContainer.glowGetterRepository
                GlowGetterViewModel(glowGetterRepository = glowGetterRepository, favoritesRepository = application.glowGetterAppContainer.favoritesRepository)
            }
        }
    }
}

