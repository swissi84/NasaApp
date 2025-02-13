package de.syntax_institut.jetpack.a04_05_online_shopper.data.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaData

import de.syntax_institut.jetpack.a04_05_online_shopper.NasaLink
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.NasaDataAssets
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.NasaLinkAssets
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.NasaVideoAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class VideoViewModel: ViewModel() {

    private val api = NasaVideoAPI.retrofitService

    private val _nasaDataAssetsState = MutableStateFlow<List<NasaDataAssets>>(emptyList())
    val nasaDataAssetsState = _nasaDataAssetsState.asStateFlow()

    private val _nasaLinksAssetsState = MutableStateFlow<List<NasaLinkAssets>>(emptyList())
    val nasaLinksAssetsState = _nasaLinksAssetsState.asStateFlow()

    init { loadNasaVideo("shuttle") }

    fun loadNasaVideo(searchQuery: String) {
        viewModelScope.launch {
            try {
                val response = api.searchVideo(searchQuery.lowercase())
                val nasaDataAssetsList = response.collection.items.flatMap { it.data }
                val nasaLinksAssetsList = response.collection.items.flatMap { it.links ?: emptyList() }

                _nasaDataAssetsState.value = nasaDataAssetsList
                _nasaLinksAssetsState.value = nasaLinksAssetsList

            } catch (e: Exception) {
                Log.e("load NasaImage", "Error: $e")
                _nasaLinksAssetsState.value = emptyList()
                _nasaDataAssetsState.value = emptyList()
            }
        }
    }
}