package com.example.qurankareem.data



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://api.alquran.cloud/v1/"

const val ENDPOINT_SURAHS = "quran/ar.alafasy"

const val ENDPOINT_SEARCH = "search/{query}/all/ar"

val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
    GsonConverterFactory.create()
).build()

val quranService = retrofit.create(QuranService::class.java)


interface QuranService {
    @GET(ENDPOINT_SURAHS)
    suspend fun getQuranData(): QuranResponse

    @GET(ENDPOINT_SEARCH)
    suspend fun searchQuran(@Path("query") query: String): SearchResponse
}