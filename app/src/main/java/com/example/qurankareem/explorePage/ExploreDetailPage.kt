package com.example.qurankareem.explorePage


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.qurankareem.R

import com.example.qurankareem.data.ExploreData
import com.example.qurankareem.data.detailHajjData

import com.example.qurankareem.data.detailSalahData
import com.example.qurankareem.data.detailSoumData
import com.example.qurankareem.data.detailZakahData


@Composable
fun ExploreDetailPage(navController: NavController, title: String) {
    Column(Modifier.fillMaxSize()) {
        ExploreTopBar(navController, title)
        Divider(Modifier.padding(horizontal = 16.dp))
        ExploreDetail(title)
    }
}

@Composable
fun ExploreDetail(title: String) {
    LazyColumn(Modifier.padding(16.dp)) {
        if (title == "الصلاة"){
            items(detailSalahData){
                ExploreDetailSalahItem(ExploreData.DetailSalahData(it.title,it.description))
            }
        }else if (title == "الصوم"){
            items(detailSoumData){
                ExploreDetailSoumItem(ExploreData.DetailSoumData(it.title,it.description))
            }
        }else if (title == "الزكاة"){
            items(detailZakahData){
                ExploreDetailZakahItem(ExploreData.DetailZakahData(it.title,it.description))
            }
        }else if (title == "الحج"){
            items(detailHajjData){
                ExploreDetailHajjItem(ExploreData.DetailHajjData(it.title,it.description))
            }
        }
    }
}

@Composable
fun ExploreDetailSalahItem(item: ExploreData.DetailSalahData) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(MaterialTheme.colorScheme.secondary.copy(0.7f))
            .border(
                1.dp,
                MaterialTheme.colorScheme.surfaceVariant.copy(0.5f),
                RoundedCornerShape(7.dp)
            )
            .clickable {
                expanded = !expanded
            }
    ) {
        Column{
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surface.copy(0.6f),
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
                Text(
                    item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, end = 8.dp, bottom = 8.dp),
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold))
                )
            }
            AnimatedVisibility(expanded) {
                Text(
                    item.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.surface.copy(0.8f),
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold))
                )
            }
        }

    }
}

@Composable
fun ExploreDetailSoumItem(item: ExploreData.DetailSoumData) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(MaterialTheme.colorScheme.secondary.copy(0.7f))
            .border(
                1.dp,
                MaterialTheme.colorScheme.surfaceVariant.copy(0.5f),
                RoundedCornerShape(7.dp)
            )
            .clickable {
                expanded = !expanded
            }
    ) {
        Column{
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surface.copy(0.6f),
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
                Text(
                    item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, end = 8.dp, bottom = 8.dp),
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold))
                )
            }
            AnimatedVisibility(expanded) {
                Text(
                    item.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.surface.copy(0.8f),
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold))
                )
            }
        }

    }
}

@Composable
fun ExploreDetailZakahItem(item: ExploreData.DetailZakahData) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(MaterialTheme.colorScheme.secondary.copy(0.7f))
            .border(
                1.dp,
                MaterialTheme.colorScheme.surfaceVariant.copy(0.5f),
                RoundedCornerShape(7.dp)
            )
            .clickable {
                expanded = !expanded
            }
    ) {
        Column{
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surface.copy(0.6f),
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
                Text(
                    item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, end = 8.dp, bottom = 8.dp),
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold))
                )
            }
            AnimatedVisibility(expanded) {
                Text(
                    item.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.surface.copy(0.8f),
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold))
                )
            }
        }

    }
}

@Composable
fun ExploreDetailHajjItem(item: ExploreData.DetailHajjData) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(MaterialTheme.colorScheme.secondary.copy(0.7f))
            .border(
                1.dp,
                MaterialTheme.colorScheme.surfaceVariant.copy(0.5f),
                RoundedCornerShape(7.dp)
            )
            .clickable {
                expanded = !expanded
            }
    ) {
        Column{
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surface.copy(0.6f),
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
                Text(
                    item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, end = 8.dp, bottom = 8.dp),
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold))
                )
            }
            AnimatedVisibility(expanded) {
                Text(
                    item.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.surface.copy(0.8f),
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold))
                )
            }
        }

    }
}

