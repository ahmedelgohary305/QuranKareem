package com.example.qurankareem.data

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Search : Routes("search")
    object SearchQuran : Routes("search_quran")
    object Profile : Routes("profile")
    object Surah : Routes("surah")
    object AboutUs : Routes("about_us")
    object Settings : Routes("settings")
    object ExploreDetail : Routes("explore")
}