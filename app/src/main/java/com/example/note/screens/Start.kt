package com.example.note.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.note.MainViewModel
import com.example.note.MainViewModelFactory
import com.example.note.navigation.NavRoute
import com.example.note.utils.TYPE_FIREBASE
import com.example.note.utils.TYPE_ROOM


@Composable
fun start (navController: NavHostController){
    val context = LocalContext.current
    val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))


    Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        Text(text = "What will be used?")
        Button(
            onClick = {
                mViewModel.initDataBase(TYPE_ROOM)
                navController.navigate(route = NavRoute.Main.route)
            },
            modifier = Modifier
                .width(200.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Room database")
        }
        Button(
            onClick = {
                mViewModel.initDataBase(TYPE_FIREBASE)
                navController.navigate(route = NavRoute.Main.route)
            },
            modifier = Modifier
                .width(200.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Firebase database")
        }
    }

}
@Preview(showBackground = true)
@Composable
fun prevStartScreen(){
    start(navController = rememberNavController())
}