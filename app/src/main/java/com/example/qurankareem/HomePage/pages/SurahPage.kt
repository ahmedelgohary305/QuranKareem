package com.example.qurankareem.HomePage.pages



import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qurankareem.HomePage.Loading
import com.example.qurankareem.R
import com.example.qurankareem.data.QuranAyah
import com.example.qurankareem.viewModels.DataState
import com.example.qurankareem.viewModels.QuranViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SurahPage(
    surahName: String,
    navController: NavController,
    startingAyah: String,
    endingAyah: String,
    quranViewModel: QuranViewModel = viewModel(),

) {
    val surahDataState by quranViewModel.surahDataState.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .clip(CircleShape)
                .height(60.dp)
                .background(MaterialTheme.colorScheme.onSecondary.copy(0.3f)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surface.copy(0.9f)
                )
            }

            Text(
                surahName,
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.surface.copy(0.8f),
            )
        }

        when(surahDataState){
            DataState.Loading ->{
                Loading()
            }
            is DataState.Success ->{
                val data = (surahDataState as DataState.Success).data
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                ){
                    data?.data?.surahs?.let {
                        items(it.filter { it.name == surahName }){
                            it.ayahs.filter {
                                it.number in startingAyah.toInt()..endingAyah.toInt()
                            }.forEach {
                                AyahItem(it)
                            }
                        }
                    }
                }
            }
            is DataState.Error -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    androidx.compose.material.Text(
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
fun AyahItem(ayah:QuranAyah) {



    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .border(1.dp,
                MaterialTheme.colorScheme.surface.copy(0.6f),
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.onSecondary.copy(0.6f)),

        contentAlignment = Alignment.Center
    ) {
        Column(Modifier.fillMaxSize()){
            Text(ayah.text,
                textAlign = TextAlign.End,
                lineHeight = 40.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(0.8f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, end = 8.dp)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    ayah.numberInSurah.toString(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleLarge
                        .copy(color = MaterialTheme.colorScheme.outline),
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )

                AyahAudio(ayah)

            }

        }
    }
}




@Composable
fun AyahAudio(ayah: QuranAyah) {



    var iconButtonOffset by remember { mutableStateOf(IntOffset.Zero) }
    val mediaPlayer = remember { MediaPlayer() }
    var isPlaying by remember { mutableStateOf(false) }
    val url = ayah.audio
    var sliderPosition by remember { mutableStateOf(0f) }
    var duration by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }
    Row(verticalAlignment = Alignment.CenterVertically){
            Slider(
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it
                    val newPosition = (it * duration).toInt()
                    mediaPlayer.seekTo(newPosition)
                },
                onValueChangeFinished = {
                    // For smoother seeking, you can start/resume playback here
                    if (isPlaying) mediaPlayer.start()
                },
                enabled = isPlaying,
                modifier = Modifier
                    .width(180.dp)
                    .height(20.dp)
                    .padding(end = 8.dp),
                colors = SliderDefaults.colors().copy(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                )
            )



        IconButton(
            modifier = Modifier
                .size(50.dp)
                .onGloballyPositioned {
                    iconButtonOffset = it
                        .positionInParent()
                        .round()
                }
                .clip(RoundedCornerShape(topStart = 10.dp))
                .background(MaterialTheme.colorScheme.primary.copy(0.6f)),

            onClick = {
                if (isPlaying) {
                    mediaPlayer.pause()
                } else {
                    try {
                        mediaPlayer.reset()
                        mediaPlayer.setDataSource(url)
                        mediaPlayer.prepareAsync()
                        mediaPlayer.setOnPreparedListener {
                            mediaPlayer.start()
                            duration = mediaPlayer.duration

                             scope.launch {
                                while (isPlaying && mediaPlayer.isPlaying) {
                                    sliderPosition = mediaPlayer.currentPosition.toFloat() / duration
                                    delay(100) // Update every 100ms
                                }
                                 if (!mediaPlayer.isPlaying) {
                                     sliderPosition = 0f
                                 }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                isPlaying = !isPlaying
            }
        ) {
            Icon(
                painter = if (isPlaying)
                    painterResource(R.drawable.baseline_pause_24)
                else
                    painterResource(R.drawable.baseline_play_arrow_24),
                contentDescription = null
            )

        }


    }
    mediaPlayer.setOnCompletionListener {
        isPlaying = false
        sliderPosition = 0f
    }
}


