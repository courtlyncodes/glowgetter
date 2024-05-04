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
import com.example.glowgetter.ui.ProductListUiState
import com.example.glowgetter.ui.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class GlowGetterViewModel(private val glowGetterRepository: GlowGetterRepository) : ViewModel() {

    var productListUiState: ProductListUiState by mutableStateOf(ProductListUiState.Loading)
        private set

    private val _productUiState = MutableStateFlow(ProductUiState())
    val productUiState: StateFlow<ProductUiState> = _productUiState.asStateFlow()

    var productQuery: String = ""
    var videoId: String = ""

  var typeQuery: String = ""
    private var subtypeQuery: String = ""

    init {
        getProductListByType(typeQuery, subtypeQuery)
    }

    fun onTypeQueryChanged(firstQuery: String, secondQuery: String?) {
        typeQuery = firstQuery
        if (secondQuery != null) {
            subtypeQuery = secondQuery
        }
        getProductListByType(typeQuery, subtypeQuery)
    }

    fun getProductListByType(type: String , subtype: String) {
        viewModelScope.launch {
            productListUiState = ProductListUiState.Loading
            productListUiState = try {
                val productList = glowGetterRepository.getProductsByType(type, subtype)
                if (productList == null) {
                    ProductListUiState.Error
                } else {
                    ProductListUiState.Success(productList)
                }
            } catch (e: IOException) {
                ProductListUiState.Error
            } catch (e: HttpException) {
                ProductListUiState.Error
            }
        }
    }

    fun updateProductCategory(category: String) {
        productQuery = category
    }

    fun updateVideoId(videoId: String) {
        this.videoId = videoId
    }



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as GlowGetterApplication)
                val glowGetterRepository = application.glowGetterAppContainer.glowGetterRepository
                GlowGetterViewModel(glowGetterRepository = glowGetterRepository)
            }
        }
    }
}