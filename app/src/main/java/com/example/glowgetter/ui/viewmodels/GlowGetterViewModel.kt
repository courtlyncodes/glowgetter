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

    private var brandQuery: String? = null
    private var typeQuery: String = "eyes"

    init {
        getProductListByBrand()
        getProductListByType()
    }

    fun onBrandQueryChanged(query: String) {
        brandQuery = query
        getProductListByBrand()
    }

    fun onTypeQueryChanged(query: String) {
        typeQuery = query
        getProductListByType()
    }

    fun getProductListByBrand(brandQuery: String? = this.brandQuery) {
        viewModelScope.launch {
            productListUiState = ProductListUiState.Loading
            productListUiState = try {
                val productList = brandQuery?.let { glowGetterRepository.getProductsByBrand(it) }

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

    fun getProductListByType(type: String = "eyeshadow") {
        viewModelScope.launch {
            productListUiState = ProductListUiState.Loading
            productListUiState = try {
                val productList = glowGetterRepository.getProductsByType(type)
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