package com.example.qurankareem.data



data class QuranResponse(
    val data : QuranData
)

data class QuranData(
    val surahs : List<QuranSura>
)

data class QuranSura(
    val number : Int,
    val name : String,
    val revelationType : String,
    val ayahs : List<QuranAyah>
)

data class QuranAyah(
    val number:Int,
    val numberInSurah:Int,
    val audio : String,
    val text : String,
)