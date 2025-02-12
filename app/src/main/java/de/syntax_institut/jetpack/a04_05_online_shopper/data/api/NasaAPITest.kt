package de.syntax_institut.jetpack.a04_05_online_shopper


import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.NasaDailyAPI
import kotlinx.coroutines.runBlocking

// Testfunktion
//fun main() = runBlocking {
//    val api = NasaImageAPI.retrofitService
//
//    try {
//        val response = api.searchData("nasa")
//        println("API Response: ${response}")
//    } catch (e: Exception) {
//        println("API Fehler: ${e.message}")
//    }
//}


fun main() = runBlocking {
    val api = NasaDailyAPI.retrofitService

    try {
        val response = api.getData()
        println("API Response: ${response}")
    } catch (e: Exception) {
        println("API Fehler: ${e.message}")
    }
}