package com.example.doisearcher.retrofit

import com.example.doisearcher.datamodels.ResponseModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object RetrofitClient {
    private const val BASE_URL = "https://api.datacite.org/"
    private var retrofit: Retrofit? = null
    val retrofitClient: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}


private const val BASE_URL =
    "https://api.datacite.org/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface DataCiteApiService {
    @GET("dois")
    suspend fun getDoisByConsortiumId(
        @Query("consortium-id") consortiumId: String
    ): ResponseModel

}

object DataCiteApi {
    val retrofitService : DataCiteApiService by lazy {
        retrofit.create(DataCiteApiService::class.java) }
}