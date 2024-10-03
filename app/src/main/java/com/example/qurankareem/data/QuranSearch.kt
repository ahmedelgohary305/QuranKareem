package com.example.qurankareem.data

data class SearchResponse(
    val data: SearchData
)

data class SearchData(
    val matches:List<SearchMatch>
)

data class SearchMatch(
    val text:String,
    val surah:Surah
)

data class Surah(
    val name:String
)