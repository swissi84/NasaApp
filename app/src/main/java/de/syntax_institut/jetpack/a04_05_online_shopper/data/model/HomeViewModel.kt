package de.syntax_institut.jetpack.a04_05_online_shopper.data.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaApiDailyImage
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.NasaDailyAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class HomeViewModel: ViewModel() {

    private val api = NasaDailyAPI.retrofitService

    private val _nasaDailyState = MutableStateFlow<NasaApiDailyImage?>(null)
    val nasaDailyState = _nasaDailyState.asStateFlow()

    init { loadNasaDailyImage() }

    fun loadNasaDailyImage() {
        viewModelScope.launch {
            try {
                val response = api.getData()
                val nasaDayliImage = response

                _nasaDailyState.value = nasaDayliImage

            } catch (e: Exception) {
                Log.e("load DailyImage", "Error: $e")
            }
        }
    }
}