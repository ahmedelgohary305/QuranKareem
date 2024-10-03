package com.example.qurankareem.HomePage.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.qurankareem.R

@Composable
fun SettingsPage(navController: NavHostController,
                 switchState: Boolean,
                 onSwitchChange: (Boolean) -> Unit
                 ) {
    Column(Modifier.fillMaxSize()) {

        Row(Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp).clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surface
                )
            }

            Text(
                stringResource(R.string.settings_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                textAlign = TextAlign.End,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontSize = 33.sp,
                color = MaterialTheme.colorScheme.surface
            )

        }

        Divider(color = MaterialTheme.colorScheme.primaryContainer.copy(0.3f), modifier = Modifier.padding(horizontal = 8.dp))

        Column(modifier = Modifier.fillMaxSize()) {
            SettingItem(switchState, onSwitchChange)
        }
    }
}


@Composable
fun SettingItem(
    switchState: Boolean,
    onSwitchChange: (Boolean) -> Unit = {}
) {

    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .padding(16.dp)
            .clip(
                RoundedCornerShape(10.dp)
            )
            .background(MaterialTheme.colorScheme.surface)
            .border(2.dp, MaterialTheme.colorScheme.secondary.copy(0.3f), RoundedCornerShape(10.dp))
    ) {
        Switch(
            modifier = Modifier.padding(start = 8.dp),
            checked = switchState,
            onCheckedChange = onSwitchChange,
        )
        Spacer(Modifier.weight(1f))
        Text(
            stringResource(R.string.swich_to_darkmode),
            modifier = Modifier.padding(end = 16.dp),
            fontFamily = FontFamily(Font(R.font.roboto_bold)),
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}