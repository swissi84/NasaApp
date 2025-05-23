package de.syntax_institut.jetpack.a04_05_online_shopper

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL2 = "https://images-api.nasa.gov/"

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
    .baseUrl(BASE_URL2)
    .build()


interface ApiImageSearch {
    @GET("search")
    suspend fun searchImage(
        @Query("q") query: String,
        @Query("media_type") mediaType: String = "image"
    ): NasaApiResponse
}

object NasaImageAPI {
    val retrofitService: ApiImageSearch by lazy { retrofit.create(ApiImageSearch::class.java) }
}



