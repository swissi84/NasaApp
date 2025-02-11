package de.syntax_institut.jetpack.a04_05_online_shopper


import kotlinx.coroutines.runBlocking

// Testfunktion
fun main() = runBlocking {
    val api = NasaAPI.retrofitService

    try {
        val response = api.searchData("nasa")
        println("API Response: ${response}")
    } catch (e: Exception) {
        println("API Fehler: ${e.message}")
    }
}
