package com.example.note.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.note.MainViewModel
import com.example.note.MainViewModelFactory
import com.example.note.model.Note
import com.example.note.navigation.NavRoute


@Composable
fun main (navController: NavHostController, viewModel: MainViewModel){

    val notes = viewModel.readAllNotes().observeAsState(listOf()).value

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
            LazyColumn{
                items(notes) {
                    note -> noteItem(note = note, navController = navController )
                }
            }
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
    val context = LocalContext.current
    val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    main(navController = rememberNavController(), viewModel = mViewModel)
}

@Composable
fun noteItem(note: Note, navController: NavHostController){
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
                Text(text = note.title, color = Color.White, fontSize = 25.sp
                    ,fontStyle = Italic )
                Text(text = note.subTitle, color = Color.White)
            }
        }
    }
}