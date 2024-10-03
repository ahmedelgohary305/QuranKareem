package com.example.qurankareem.HomePage


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, onSearchClick:() -> Unit,onDrawerClick:() -> Unit = {}) {
    TopAppBar(
        modifier = Modifier
            .windowInsetsPadding(
                WindowInsets.statusBars.only(WindowInsetsSides.Top)
            )
            .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp)
            .border(4.dp, MaterialTheme.colorScheme.secondary.copy(0.2f), CircleShape)
            .height(70.dp)
            .clip(CircleShape),

        title = {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) { Text(title, fontSize = 27.sp) }
        },
        navigationIcon = {
            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clip(CircleShape)
                        .clickable {
                            onDrawerClick()
                        }
                )
            }
        },
        actions = {
            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clip(CircleShape)
                        .clickable {
                           onSearchClick()
                        }
                )
            }
            // Add more actions if needed
        },
        colors = TopAppBarColors(
            MaterialTheme.colorScheme.surfaceVariant.copy(0.9f),
            Color.Gray,
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.outline,
            MaterialTheme.colorScheme.secondary,
        )

    )
}