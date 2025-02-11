package de.syntax_institut.jetpack.a04_05_online_shopper

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


open class NasaViewModel: ViewModel() {

    private val api = NasaAPI.retrofitService

    private val _nasaDataState = MutableStateFlow<List<NasaData>>(emptyList())
    val nasaDataState = _nasaDataState.asStateFlow()

    private val _nasaLinksState = MutableStateFlow<List<NasaLink>>(emptyList())
    val nasaLinksState = _nasaLinksState.asStateFlow()

    init { loadNasaItems("shuttle") }

    fun loadNasaItems(searchQuery: String) {
        viewModelScope.launch {
            try {
                val response = api.searchData(searchQuery.lowercase())
                val nasaDataList = response.collection.items.flatMap { it.data }
                val nasaLinksList = response.collection.items.flatMap { it.links ?: emptyList() }

                _nasaDataState.value = nasaDataList
                _nasaLinksState.value = nasaLinksList

            } catch (e: Exception) {
                Log.e("loadProducts", "Error: $e")
            }
        }
    }
}
