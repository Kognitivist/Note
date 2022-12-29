package com.example.note.screens

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.note.navigation.NavRoute


@Composable
fun main (navController: NavHostController){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 10.dp, top = 8.dp)
        , horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.SpaceBetween) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 8.dp)
            , horizontalAlignment = Alignment.CenterHorizontally
            , verticalArrangement = Arrangement.Top) {
            noteItem(title = "Note 1", subtitle = "text", navController = navController)
            noteItem(title = "Note 2", subtitle = "text", navController = navController)
            noteItem(title = "Note 3", subtitle = "text", navController = navController)
            noteItem(title = "Note 4", subtitle = "text", navController = navController)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            , verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.Center) {
            FloatingActionButton(
                onClick = {navController.navigate(route = NavRoute.Add.route)}
            ) {
                Icon(imageVector = Icons.Filled.Add , contentDescription = "", tint = Color.White)
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun prevMainScreen(){
    main(navController = rememberNavController())
}

@Composable
fun noteItem(title:String, subtitle:String, navController: NavHostController){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .clickable { navController.navigate(route = NavRoute.Note.route) }
            ,backgroundColor = Color.Blue)
        {
            Column(modifier = Modifier
                .fillMaxSize()
                , horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = title, color = Color.White, fontSize = 25.sp
                    ,fontStyle = Italic )
                Text(text = subtitle, color = Color.White)
            }
        }
    }
}