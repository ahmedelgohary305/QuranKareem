package com.example.qurankareem.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.qurankareem.R
import com.example.qurankareem.data.Routes
import com.example.qurankareem.data.bottomItems

@Composable
fun BottomBar(navController: NavHostController, currentDestination:String) {



   BottomNavigation(
      backgroundColor = MaterialTheme.colorScheme.surfaceContainer,
      modifier = Modifier
      .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
   ) {
      bottomItems.forEach {
         val selected = it.route == currentDestination
         val color = if (selected) MaterialTheme.colorScheme.onBackground.copy(0.9f)else
               MaterialTheme.colorScheme.onBackground.copy(0.3f)

         val size = if (selected) 1.3f else 1f


         BottomNavigationItem(
            modifier = Modifier.padding(vertical =  10.dp),
            icon = {
               Column(horizontalAlignment = Alignment.CenterHorizontally){
                  Icon(
                     painter = painterResource(id = it.icon), contentDescription = null,
                     tint = color,
                     modifier = Modifier
                        .size(if(it.icon == R.drawable.download__4_) 38.dp else 30.dp)
                        .scale(size)
                  )

                  Text( stringResource(id = it.title),
                     fontSize = 15.sp,
                     color = color,
                     fontFamily = FontFamily(Font(R.font.roboto_bold)),
                     modifier = Modifier
                        .padding(top = if (it.icon == R.drawable.download__4_) 4.dp else 8.dp)
                        .scale(size)
                  )
               }
            },
            onClick = {
             navController.navigateSingleTopTo(it.route)

            },
            selected = it.route == currentDestination,
         )


      }
   }
}



fun NavHostController.navigateSingleTopTo(route: String) =
 this.navigate(route){
   popUpTo(Routes.Home.route){
      saveState = true
   }
   this@navigateSingleTopTo.graph.startDestinationRoute?.let {
         route ->
      popUpTo(route){
      }

   }
   launchSingleTop = true
   restoreState = true
}