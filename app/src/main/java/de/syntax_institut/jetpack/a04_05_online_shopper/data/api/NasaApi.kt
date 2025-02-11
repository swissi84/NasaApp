package de.syntax_institut.jetpack.a04_05_online_shopper

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://images-api.nasa.gov/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface APIService {
    @GET("search")
    suspend fun searchData(@Query("q") query: String ): NasaApiResponse
}

object NasaAPI {
    val retrofitService: APIService by lazy { retrofit.create(APIService::class.java) }
}
