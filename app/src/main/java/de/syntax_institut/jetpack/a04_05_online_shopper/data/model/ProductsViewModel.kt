package de.syntax_institut.jetpack.a04_05_online_shopper

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class ProductsViewModel: ViewModel() {

    private val api = ProductAPI.retrofitService

    private val _productState = MutableStateFlow<List<Product>>(emptyList())
    open val productState = _productState.asStateFlow()

    init { loadProducts() }

    fun loadProducts() {
        viewModelScope.launch {
            try {

                val response = api.getArticles()

                _productState.value = response + _productState.value

            } catch (e: Exception) {
                Log.e("loadOneMeal", "Error: $e")
            }
        }
    }
}
