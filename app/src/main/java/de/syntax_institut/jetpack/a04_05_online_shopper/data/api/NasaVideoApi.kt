package de.syntax_institut.jetpack.a04_05_online_shopper.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntax_institut.jetpack.a04_05_online_shopper.BASE_URL2
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL3 = "https://images-api.nasa.gov/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val loggingInterceptor2 = HttpLoggingInterceptor().apply {
    // Logging Levels: BODY, BASIC, NONE, HEADERS
    level = HttpLoggingInterceptor.Level.BODY
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor2)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(okHttpClient)
    .baseUrl(BASE_URL3)
    .build()


interface ApiVideoSearch {
    @GET("search")
    suspend fun searchVideo(
        @Query("q") query: String,
        @Query("media_type") mediaType: String = "video"
    ): NasaApiResponseAssets
}

object NasaVideoAPI {
    val retrofitService: ApiVideoSearch by lazy { retrofit.create(ApiVideoSearch::class.java) }
}