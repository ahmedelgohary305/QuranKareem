package com.example.qurankareem.data

class QuranRepository() {
    suspend fun getQuranData(): QuranResponse =
         quranService.getQuranData()

    suspend fun searchQuran(query: String): SearchResponse =
        quranService.searchQuran(query)
}