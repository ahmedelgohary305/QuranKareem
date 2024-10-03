package com.example.qurankareem.data

import androidx.annotation.DrawableRes
import com.example.qurankareem.R

sealed class DrawerItems(val title:String,val route:String,@DrawableRes val icon :Int){
    object AboutUs:DrawerItems(
        title = R.string.drawer_about_us.toString(),
        route = Routes.AboutUs.route,
        icon = R.drawable.baseline_person_24
    )
    object Settings:DrawerItems(
        title = R.string.drawer_settings.toString(),
        route = Routes.Settings.route,
        icon = R.drawable.baseline_settings_24
    )

}

val drawerItems = listOf(
    DrawerItems.AboutUs,
    DrawerItems.Settings

)