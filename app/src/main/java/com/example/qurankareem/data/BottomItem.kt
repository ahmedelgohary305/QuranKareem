package com.example.qurankareem.data


import com.example.qurankareem.R

data class BottomItem(
    val title: Int,
    val icon: Int,
    val route: String
)

val bottomItems = listOf(
    BottomItem(R.string.Home, R.drawable.download__4_, Routes.Home.route),
    BottomItem(R.string.Search, R.drawable.icons8_search__2_, Routes.Search.route)

)