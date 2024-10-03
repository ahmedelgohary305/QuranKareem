package com.example.qurankareem.HomePage.pages


import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import androidx.navigation.NavController
import com.example.qurankareem.R
import com.example.qurankareem.viewModels.DataState
import com.example.qurankareem.viewModels.QuranViewModel


@Composable
fun SearchQuran(navController: NavController, quranViewModel: QuranViewModel) {

    val listState = rememberLazyListState()
    val scrollPercentage = calculateScrollPercentage(listState)
    val isSearching by quranViewModel.isSearching.collectAsStateWithLifecycle()
    val dataState by quranViewModel.dataState.collectAsStateWithLifecycle()

    val focusRequester = remember { FocusRequester() }
    var searchText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue()
        )
    }


    Column(modifier = Modifier.fillMaxSize()) {
        SearchField(
            searchText,
            onValueChange = { searchText = it },
            keyboardType = KeyboardType.Text,
            onSearchClick = {
                quranViewModel.searchReset()

                if (searchText.text.isNotEmpty()) {
                    quranViewModel.searchQuran(searchText.text)
                }
            },
            onClearClicked = {
                searchText = TextFieldValue()
            },
            onBackClicked = {
                navController.popBackStack()
            },
            focusRequester = focusRequester,
        )




        when (dataState) {
            DataState.Loading -> {
                if(isSearching){
                    LoadingIndicator(130.dp,270.dp,200.dp)
                }
            }
            is DataState.Success -> {
                val data = (dataState as DataState.Success).data
                Box{
                    LazyColumn(modifier = Modifier, state = listState) {
                        items(data?.data?.matches.orEmpty()) {
                            SearchResult(it.text, it.surah.name)
                        }
                    }
                    VerticalScrollIndicator(scrollPercentage)
                }
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

    }

}

@Composable
fun LoadingIndicator(size1:Dp,size2:Dp,size3: Dp) {
    val transition = rememberInfiniteTransition(label = "")
    val alpha by transition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = 900),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    LazyColumn {
        item{
            Spacer(Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .height(size1)
                .alpha(alpha)
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
            )
        }
        item{
            Spacer(Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .height(size2)
                .alpha(alpha)
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
            )
        }
        item{
            Spacer(Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .height(size3)
                .alpha(alpha)
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
            )
        }
    }
}

@Composable
fun VerticalScrollIndicator(scrollPercentage: Float) {
    val indicatorHeight = 30.dp
    val indicatorWidth = 4.dp
    val indicatorColor = MaterialTheme.colorScheme.background

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(indicatorWidth)
            .padding(end = 4.dp, start = 4.dp, top = 16.dp)
            .drawWithContent {
                val canvasHeight = size.height
                val indicatorTop = scrollPercentage * canvasHeight
                drawRect(
                    color = indicatorColor,
                    topLeft = Offset(0f, indicatorTop),
                    size = Size(indicatorWidth.toPx(), indicatorHeight.toPx())
                )
            }
    )
}

private fun calculateScrollPercentage(listState: LazyListState): Float {
    val visibleItems = listState.layoutInfo.visibleItemsInfo
    if (visibleItems.isEmpty()) return 0f

    val firstVisibleItem = visibleItems[0]

    val totalItemsCount = listState.layoutInfo.totalItemsCount
    val totalScrollRange = if (totalItemsCount <= 1) 1f else totalItemsCount - 1f
    val currentScrollPosition = firstVisibleItem.index + (firstVisibleItem.offset / firstVisibleItem.size)

    return currentScrollPosition / totalScrollRange
}


@Composable
fun SearchResult(ayah: String, suraName: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .border(
                2.dp,
                MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.surface.copy(0.9f))

    ) {
        Column(Modifier.fillMaxSize()) {
            Text(
                ayah,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(end = 16.dp, top = 8.dp, start = 16.dp),
                fontSize = 21.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                suraName, Modifier.padding(start = 16.dp, bottom = 8.dp),
                fontSize = 19.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                color = MaterialTheme.colorScheme.primary.copy(0.7f)
            )
        }
    }
}


@Composable
fun SearchField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    keyboardType: KeyboardType,
    onSearchClick: () -> Unit,
    onClearClicked: () -> Unit,
    onBackClicked: () -> Unit,
    focusRequester: FocusRequester,

) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = MaterialTheme.colorScheme.surface.copy(0.8f),
            cursorColor = MaterialTheme.colorScheme.onSurface.copy(0.3f),
            textColor = MaterialTheme.colorScheme.onSurface.copy(0.7f),
        ),
        shape = RoundedCornerShape(30.dp),

        leadingIcon = {
            if (value.text.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(0.9f),
                    modifier = Modifier.clickable {
                        onClearClicked()
                    }
                )
            }
        },

        trailingIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(0.9f),
                modifier = Modifier.clickable {
                    onBackClicked()
                }
            )
        },

        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp)
            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions {
            if (value.text.isNotEmpty()) onSearchClick()
        },
        maxLines = 1,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End, fontSize = 18.sp)

    )

    LaunchedEffect(Unit) {
            focusRequester.requestFocus()
    }
}