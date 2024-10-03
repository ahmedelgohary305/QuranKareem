package com.example.qurankareem.HomePage


import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.qurankareem.HomePage.pages.JuzsPage
import com.example.qurankareem.HomePage.pages.SurahsPage
import com.example.qurankareem.R
import com.example.qurankareem.viewModels.DataState
import com.example.qurankareem.viewModels.QuranViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun HomePage(
    quranViewModel: QuranViewModel,
    navController: NavController,
) {
    val quranData by quranViewModel.quranData.collectAsStateWithLifecycle()
    val surahDataState by quranViewModel.surahDataState.collectAsStateWithLifecycle()

    val tabsStateHandler by remember {
        mutableStateOf(TabsStateHandler())
    }

    Column(Modifier.fillMaxSize()) {
        TabsItem(tabsStateHandler)
        when (tabsStateHandler.selectedTabIndex.value) {
            0 -> when (surahDataState) {
                DataState.Loading -> {
                    Loading()
                }

                is DataState.Success -> {
                    val data = (surahDataState as DataState.Success).data
                    SurahsPage(navController, data)
                }

                is DataState.Error -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            stringResource(R.string.no_connection_error),
                            fontSize = 22.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_bold)),
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                }
            }

            1 -> JuzsPage(quranData, navController)
        }


    }
}


@Composable
fun Loading() {
    val transition = rememberInfiniteTransition()
    val size by transition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1000), repeatMode = RepeatMode.Reverse),
        label = ""
    )


    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surface.copy(0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Spacer(
                Modifier
                    .size((50 * size).dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    }
}


@Composable
fun TabsItem(tabsStateHandler: TabsStateHandler) {

    val color1 by animateColorAsState(
        if (tabsStateHandler.selectedTabIndex.value == 0)
            MaterialTheme.colorScheme.surface.copy(0.6f)
        else
            MaterialTheme.colorScheme.surface.copy(0.3f),
        label = ""
    )
    val color2 by animateColorAsState(
        if (tabsStateHandler.selectedTabIndex.value == 1)
            MaterialTheme.colorScheme.surface.copy(0.6f)
        else
            MaterialTheme.colorScheme.surface.copy(0.3f),
        label = ""
    )


    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, MaterialTheme.colorScheme.surface.copy(0.4f), RoundedCornerShape(20))
            .clip(RoundedCornerShape(20))
            .background(MaterialTheme.colorScheme.secondary)

    ) {
        Button(
            onClick = { tabsStateHandler.changeIndex(0) },
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .height(50.dp),
            colors = ButtonColors(
                containerColor = color1,
                contentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified,
                disabledContentColor = Color.Unspecified
            )
        ) {
            Text(
                "السور",
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 21.sp
            )
        }
        Button(
            onClick = { tabsStateHandler.changeIndex(1) },
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .height(50.dp),
            colors = ButtonColors(
                containerColor = color2,
                contentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified,
                disabledContentColor = Color.Unspecified
            )
        ) {
            Text(
                "الاجزاء",
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 21.sp
            )
        }
    }
}






