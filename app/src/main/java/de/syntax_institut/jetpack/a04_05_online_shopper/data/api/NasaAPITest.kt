package de.syntax_institut.jetpack.a04_05_online_shopper

import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.NasaVideoAPI
import kotlinx.coroutines.runBlocking


/*fun main() = runBlocking {
    val api = NasaImageAPI.retrofitService

    try {
        val response = api.searchImage("nasa")
        println("API Response: ${response}")
    } catch (e: Exception) {
        println("API Fehler: ${e.message}")
    }
}*/


/*
fun main() = runBlocking {
    val api = NasaDailyAPI.retrofitService

    try {
        val response = api.getData()
        println("API Response: ${response}")
    } catch (e: Exception) {
        println("API Fehler: ${e.message}")
    }
}*/


fun main() = runBlocking {
    val api = NasaVideoAPI.retrofitService

    try {
        val response = api.searchVideo("nasa")
        println("API Response: ${response}")
    } catch (e: Exception) {
        println("API Fehler: ${e.message}")
    }
}
