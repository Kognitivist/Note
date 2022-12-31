package com.example.note.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.note.MainViewModel
import com.example.note.MainViewModelFactory


@Composable
fun note (navController: NavHostController, viewModel: MainViewModel){
    Column(modifier = Modifier.fillMaxSize()
        , horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Center) {
        Card(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)) {
            Column(modifier = Modifier.padding(vertical = 8.dp)
                , horizontalAlignment = Alignment.CenterHorizontally
                , verticalArrangement = Arrangement.Center)
            {
                Text(text = "title", fontSize = 25.sp
                    ,fontStyle = FontStyle.Italic
                )
                Text(text = "subtitle")
            }
            
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun prevNoteScreen(){
    val context = LocalContext.current
    val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    note(navController = rememberNavController(), viewModel = mViewModel)
}