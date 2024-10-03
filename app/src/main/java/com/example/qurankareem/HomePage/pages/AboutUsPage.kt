package com.example.qurankareem.HomePage.pages

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.qurankareem.R
import java.io.IOException

@Composable
fun AboutUsPage(navController: NavController) {

     Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
         Icon(
             imageVector = Icons.AutoMirrored.Filled.ArrowBack,
             contentDescription = null,
             modifier = Modifier.padding(16.dp).align(Alignment.Start).clickable {
                 navController.popBackStack()
             },
             tint = MaterialTheme.colorScheme.surface
         )
         MyImage("images/MyPhoto.jpg")
         Divider(Modifier.padding(horizontal = 64.dp, vertical = 2.dp))
         AboutMeParagraph()
         Divider(Modifier.padding(horizontal = 64.dp, vertical = 2.dp))
     }
}

@Composable
fun AboutMeParagraph() {
        Text(
            text = stringResource(R.string.about_me_paragraph),
            color = MaterialTheme.colorScheme.surfaceVariant,
            lineHeight = 26.sp,
            fontSize = 23.sp,
            fontFamily = FontFamily(Font(R.font.roboto_bold)),
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
}


@Composable
fun MyImage(filePath: String) {
    val context = LocalContext.current
    val imageBitmap = remember(filePath) {
        try {
            val inputStream = context.assets.open(filePath) // Access the file
            BitmapFactory.decodeStream(inputStream) // Decode to Bitmap
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    imageBitmap?.let { bitmap ->
        Image(
            bitmap = bitmap.asImageBitmap(), // Convert to ImageBitmap
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 8.dp, top = 8.dp)
                .size(300.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.tertiary, CircleShape)

        )
    }
}