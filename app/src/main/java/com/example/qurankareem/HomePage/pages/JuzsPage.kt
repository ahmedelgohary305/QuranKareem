package com.example.qurankareem.HomePage.pages


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.qurankareem.R
import com.example.qurankareem.data.QuranResponse
import com.example.qurankareem.data.QuranSura


@SuppressLint("SuspiciousIndentation")
@Composable
fun JuzsPage(quranData: QuranResponse?,
             navController: NavController,
) {


    val surahsData = quranData?.data?.surahs ?: emptyList()


    LazyColumn(Modifier.fillMaxSize()) {
        items(30) {
            index ->
            JuzItem(surahsData,index + 1,navController)
        }
    }
}




@Composable
fun JuzItem(surahsData: List<QuranSura>,juzNumber:Int,navController: NavController) {

var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(10.dp))
            .animateContentSize()
            .background(MaterialTheme.colorScheme.primaryContainer.copy(0.9f)),
    ) {
        Row(Modifier.fillMaxWidth().clickable {
            expanded = !expanded
        },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(
                onClick = {
                    expanded = !expanded
                },
            ) {
                Icon(
                    painter = if (expanded)
                        painterResource(R.drawable.baseline_arrow_drop_up_24)
                        else
                        painterResource(R.drawable.baseline_arrow_drop_down_24) ,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary.copy(0.7f),
                    modifier = Modifier.size(32.dp),
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(juzNumber.toString(), fontSize = 22.sp)

                Text(
                    text = "الجزء رقم",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    textAlign = TextAlign.End
                )
            }
        }

        if (expanded){
            JuzSurahsList(surahsData,juzNumber,navController)
        }
    }
}


@Composable
fun JuzSurahsList(surahsData: List<QuranSura>,juzNumber: Int,navController: NavController) {
         surahsData.forEach {
               when(it.number){
                   in 1..2 -> {
                       if (juzNumber == 1) SurahItem(it, navController)
                      else if (juzNumber == 1 && it.number == 2) SurahItem(it, navController,8,148)
                      else if (juzNumber == 2 && it.number == 2) SurahItem(it, navController,149,259)
                       else if (juzNumber == 3 && it.number == 2) SurahItem(it, navController,260,293)
                   } // Juz 1
                   in 2..3 -> {
                       if (juzNumber == 3) SurahItem(it, navController,294,385)
                       else if (juzNumber == 4) SurahItem(it, navController,386,493)
                   }// Juz 3
                   in 3..4 -> {
                       if (juzNumber == 4) SurahItem(it, navController,494,516)
                       else if (juzNumber == 5) SurahItem(it, navController,517,640 )
                       else if (juzNumber == 6) SurahItem(it, navController,641,669)
                   } // Juz 4
                   in 4..5 -> {
                       if (juzNumber == 6) SurahItem(it, navController,670,750)
                       else if (juzNumber == 7) SurahItem(it, navController,751,789)
                   } // Juz 6
                   in 5..6 -> {
                       if (juzNumber == 7) SurahItem(it, navController,790,899)
                       else if (juzNumber == 8) SurahItem(it, navController,900,954 )
                   } // Juz 7
                   in 6..7 -> {
                       if (juzNumber == 8) SurahItem(it, navController,955,1041)
                       else if (juzNumber == 9) SurahItem(it, navController,1042,1160)
                   }// Juz 8
                   in 7..8 -> {
                       if (juzNumber == 9) SurahItem(it, navController,1161,1200)
                       else if (juzNumber == 10) SurahItem(it, navController,1201,1235)
                   } // Juz 9
                   in 8..9 -> {
                       if (juzNumber == 10) SurahItem(it, navController,1236,1327 )
                       else if (juzNumber == 11 && it.number == 10) SurahItem(it, navController)
                       else if (juzNumber == 11) SurahItem(it, navController,1328,1364)

                   }// Juz 10
                   in 9..11 -> {
                       if (juzNumber == 11) SurahItem(it, navController,1474,1478)
                       else if (juzNumber == 12 && it.number == 11) SurahItem(it, navController,1479,1596)
                   } // Juz 11
                   in 11..12 -> {
                       if (juzNumber == 12) SurahItem(it, navController,1597,1648)
                       else if (juzNumber == 13) SurahItem(it, navController,1649,1707)
                   } // Juz 12
                   in 12..14 -> {
                        if (juzNumber == 13 && it.number == 13) SurahItem(it, navController,)
                        else if (juzNumber == 13) SurahItem(it, navController,1751,1802)

                   } // Juz 13
                   in 15..16 -> {
                       if (juzNumber == 14 && it.number == 15) SurahItem(it, navController,1803,1901)
                       if (juzNumber == 14 && it.number == 16) SurahItem(it, navController,1902,2029)
                   }// Juz 14
                   in 17..18 -> {
                       if (juzNumber == 15 && it.number == 17) SurahItem(it, navController,2030,2141 )
                       else if (juzNumber == 15 && it.number == 18) SurahItem(it, navController,2142,2215)
                       else if (juzNumber == 16 && it.number == 18) SurahItem(it, navController,2215,2250)
                   } // Juz 15
                   in 18..20 -> {
                       if (juzNumber == 16) SurahItem(it, navController)
                       else if(juzNumber == 16 && it.number == 20) SurahItem(it, navController,2350,2485)
                   }// Juz 16
                   in 21..22 -> {
                       if (juzNumber == 17) SurahItem(it, navController)
                      else if (juzNumber == 17 && it.number == 22) SurahItem(it, navController,2598,2675)
                   }// Juz 17
                   in 23..25 -> {
                        if (juzNumber == 18 && it.number == 25) SurahItem(it, navController,2856,2875)
                       else if (juzNumber == 18) SurahItem(it, navController)
                       else if (juzNumber == 19 && it.number == 25) SurahItem(it, navController,2876,2934)
                   } // Juz 18
                   in 25..27 -> {

                        if (juzNumber == 19 && it.number == 27) SurahItem(it, navController,3160,3214)
                        else if (juzNumber == 19) SurahItem(it, navController)
                        else if (juzNumber == 20 && it.number == 27) SurahItem(it, navController,3215,3252)
                   }// Juz 19
                   in 27..29 -> {

                        if (juzNumber == 20 && it.number == 29) SurahItem(it, navController,3341,3385)
                        else if (juzNumber == 20) SurahItem(it, navController)
                        else if (juzNumber == 21 && it.number == 29) SurahItem(it, navController,3386,3409)
                   } // Juz 20
                   in 29..33 -> {

                        if (juzNumber == 21 && it.number == 33) SurahItem(it, navController,3534,3563)
                        else if (juzNumber == 21) SurahItem(it, navController)

                       else if (juzNumber == 22 && it.number == 33) SurahItem(it, navController,3564,3606 )
                   } // Juz 21
                   in 33..36 -> {

                        if (juzNumber == 22 && it.number == 36) SurahItem(it, navController,3706,3732)
                        else if (juzNumber == 22) SurahItem(it, navController)

                       else if (juzNumber == 23 && it.number == 36) SurahItem(it, navController,3733,3788)
                   } // Juz 22
                   in 36..39 -> {
                        if (juzNumber == 23 && it.number == 39) SurahItem(it, navController,4059,4089)
                        else if (juzNumber == 23) SurahItem(it, navController)

                       else if (juzNumber == 24 && it.number == 39) SurahItem(it, navController,4090,4133)
                   }  // Juz 23
                   in 39..40 -> if (juzNumber == 24) SurahItem(it, navController)  // Juz 24
                   in 41..45 -> {
                       if (juzNumber == 25 && it.number == 41) SurahItem(it, navController,4265,4272)
                       else if (juzNumber == 25) SurahItem(it, navController)
                       else if(juzNumber == 24 && it.number == 41) SurahItem(it,navController,4219,4264)
                   } // Juz 25
                   in 46..51 -> {

                        if (juzNumber == 26 && it.number == 51) SurahItem(it, navController,4634,4705)
                       else if (juzNumber == 26) SurahItem(it, navController)
                       else if (juzNumber == 27 && it.number == 51) SurahItem(it, navController,4706,4738)
                   }  // Juz 26
                   in 51..57 -> if (juzNumber == 27) SurahItem(it, navController) // Juz 27
                   in 58..66 -> if (juzNumber == 28) SurahItem(it, navController)  // Juz 28
                   in 67..77 -> if (juzNumber == 29) SurahItem(it, navController)  // Juz 29
                   in 78..114 -> if (juzNumber == 30) SurahItem(it, navController) // Juz 30
               }
         }
     }

