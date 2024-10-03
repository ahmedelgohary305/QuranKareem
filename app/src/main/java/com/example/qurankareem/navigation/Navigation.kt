package com.example.qurankareem.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.qurankareem.HomePage.HomePage
import com.example.qurankareem.HomePage.TopBar
import com.example.qurankareem.HomePage.pages.AboutUsPage
import com.example.qurankareem.HomePage.pages.SearchQuran
import com.example.qurankareem.HomePage.pages.SettingsPage
import com.example.qurankareem.HomePage.pages.SurahPage
import com.example.qurankareem.ProfilePage
import com.example.qurankareem.R
import com.example.qurankareem.explorePage.ExplorePage
import com.example.qurankareem.components.BottomBar
import com.example.qurankareem.components.navigateSingleTopTo
import com.example.qurankareem.data.DrawerItems
import com.example.qurankareem.data.Routes
import com.example.qurankareem.data.drawerItems
import com.example.qurankareem.explorePage.ExploreDetailPage
import com.example.qurankareem.viewModels.QuranViewModel
import kotlinx.coroutines.launch


@Composable
fun DrawerItem(
    item: DrawerItems,
    onDrawerItemClicked: () -> Unit
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.onBackground.copy(0.7f))
            .clickable {
                onDrawerItemClicked()
            }
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.primaryContainer,
            painter = painterResource(id = item.icon),
            contentDescription = null,
            modifier = Modifier.padding(end = 8.dp, top = 4.dp, start = 4.dp)
        )

        Text(
            color = MaterialTheme.colorScheme.surfaceVariant,
            text = stringResource(item.title.toInt()),
            fontFamily = FontFamily(Font(R.font.roboto_bold)),
            fontSize = 25.sp
        )
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    switchState: Boolean,
    onSwitchChange: (Boolean) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val quranViewModel: QuranViewModel = viewModel()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination?.route
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(250.dp),
                drawerShape = RectangleShape,
                drawerContainerColor = MaterialTheme.colorScheme.surface,
            ) {
                LazyColumn(Modifier.padding(16.dp)) {
                    items(drawerItems) {
                        DrawerItem(item = it) {
                            navController.navigateSingleTopTo(it.route)
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    }
                }
            }
        }
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.primary.copy(0.9f),
            topBar = {
                AnimatedVisibility(
                    visible = currentDestination == Routes.Home.route,
                    enter = fadeIn(
                        animationSpec = tween(100)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(100)
                    )
                ) {
                    TopBar(stringResource(R.string.app_title),
                        { navController.navigateSingleTopTo(Routes.SearchQuran.route) }
                    ) {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                }
            },
            bottomBar = {

                if (currentDestination in listOf(
                        Routes.Home.route,
                        Routes.Search.route,
                        Routes.Profile.route
                    )
                )
                    BottomBar(navController, currentDestination ?: Routes.Home.route)


            }
        ) {
            NavHost(
                navController = navController,
                startDestination = Routes.Home.route,
                enterTransition = {
                    fadeIn(
                        animationSpec = tween(100)
                    )
                },
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(100)
                    )
                },
                modifier = Modifier.padding(it)
            ) {
                composable(Routes.Home.route) {
                    HomePage(navController = navController, quranViewModel = quranViewModel)
                }
                composable(Routes.Search.route) {
                    ExplorePage(navController)

                }
                composable(Routes.Profile.route) {
                    ProfilePage()

                }
                composable(Routes.Surah.route + "/{surahName}/{startingAyah}/{endingAyah}") {
                    val surahName = it.arguments?.getString("surahName") ?: ""
                    val startingAyah = it.arguments?.getString("startingAyah") ?: "0"
                    val endingAyah = it.arguments?.getString("endingAyah") ?: "0"
                    SurahPage(surahName, navController, startingAyah, endingAyah)
                }

                composable(Routes.SearchQuran.route) {
                    SearchQuran(navController, quranViewModel = quranViewModel)
                }
                composable(Routes.AboutUs.route) {
                    AboutUsPage(navController)
                }
                composable(Routes.Settings.route) {
                    SettingsPage(navController, switchState, onSwitchChange)
                }
                composable(Routes.ExploreDetail.route+"/{title}") {
                    val title = it.arguments?.getString("title") ?: ""
                    ExploreDetailPage(navController,title)
                }
            }
        }
    }
}