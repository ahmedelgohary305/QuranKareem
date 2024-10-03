package com.example.qurankareem.HomePage.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.qurankareem.R
import com.example.qurankareem.data.QuranResponse
import com.example.qurankareem.data.QuranSura
import com.example.qurankareem.data.Routes


@Composable
fun SurahsPage(navController: NavController,quranData:QuranResponse?) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        quranData?.data?.surahs?.forEach {
            item {
                SurahItem(it,navController = navController)
            }
        }

    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun SurahItem(surahItem: QuranSura,
              navController: NavController,
              startingAyah:Int = surahItem.ayahs.first().number,
              endingAyah:Int = surahItem.ayahs.last().number
) {

    val icon = if (surahItem.revelationType == "Meccan")
        painterResource(id = R.drawable.download__1_)
    else
        painterResource(id = R.drawable.download__2_)

    val size = if (surahItem.revelationType == "Medinan")
        49.dp
    else
        42.dp

    Box(modifier = Modifier
        .padding(horizontal = 12.dp, vertical = 4.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(20))
        .height(60.dp)
        .background(MaterialTheme.colorScheme.primaryContainer.copy(0.9f))
        .clickable {
            navController.navigate(
                Routes.Surah.route +
                        "/${surahItem.name}/${startingAyah}/${endingAyah}"
            )
        }
    ) {
        Row(Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    surahItem.number.toString(),
                    modifier = Modifier.padding(start = 16.dp),
                    color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
                )
                Icon(painter = icon,
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(size)
                        .padding(horizontal = 8.dp)
                        .alpha(0.9f)
                )
            }

            Text(surahItem.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                style = MaterialTheme.typography.bodyLarge
            )

        }



    }



}