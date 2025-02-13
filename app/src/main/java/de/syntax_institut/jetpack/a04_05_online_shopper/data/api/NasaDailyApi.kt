package de.syntax_institut.jetpack.a04_05_online_shopper.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntax_institut.jetpack.a04_05_online_shopper.ApiImageSearch
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaApiDailyImage
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.nasa.gov/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val loggingInterceptor = HttpLoggingInterceptor().apply {
    // Logging Levels: BODY, BASIC, NONE, HEADERS
    level = HttpLoggingInterceptor.Level.BODY
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(okHttpClient)
    .baseUrl(BASE_URL)
    .build()


interface ApiDailyImage {
    @GET("planetary/apod?api_key=mMc1TD9br8iiPQX7Le8WsHqbZL7OjGgj7SB3NjlS")
    suspend fun getData(): NasaApiDailyImage
}

object NasaDailyAPI {
    val retrofitService: ApiDailyImage by lazy { retrofit.create(ApiDailyImage::class.java) }
}
