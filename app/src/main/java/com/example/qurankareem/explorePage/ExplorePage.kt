package com.example.qurankareem.explorePage


import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.qurankareem.R
import com.example.qurankareem.data.ExploreData
import com.example.qurankareem.data.Routes
import com.example.qurankareem.data.exploreData
import kotlinx.coroutines.delay


@Composable
fun ExplorePage(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        ExploreTopBar(navController, stringResource(R.string.explore_title))

        Divider(Modifier.padding(horizontal = 16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            items(exploreData) {
                ExploreItem(exploreItem = it){
                     navController.navigate(Routes.ExploreDetail.route+"/${it.title}")
                }
            }
        }
    }
}

@Composable
fun ExploreTopBar(navController: NavController,title:String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(start = 8.dp).clickable {
                navController.popBackStack()
            }
        )
        Text(
            title,
            modifier = Modifier.padding(end = 8.dp),
            fontSize = 35.sp,
            color = MaterialTheme.colorScheme.surface
        )

    }
}

@Composable
fun ExploreItem(
    exploreItem: ExploreData,
    onItemClick:() -> Unit
) {
    var isClicked by remember { mutableStateOf(false) }
    Box(
        Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onItemClick()
                isClicked = !isClicked
            }
            .padding(8.dp)
            .width(180.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.tertiary.copy(0.6f))
            .border(
                1.dp,
                MaterialTheme.colorScheme.surface.copy(0.4f),
                RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(exploreItem.icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = exploreItem.title,
                modifier = Modifier.padding(end = 8.dp),
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.surface
            )
        }
    }
}
