package com.example.note

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.note.navigation.NavRoute
import com.example.note.navigation.NotesNavHost
import com.example.note.utils.DB_TYPE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
            val navController = rememberNavController()
            Column {
                TopAppBar(
                backgroundColor = Color.Blue,
                contentColor = Color.White,
                elevation = 12.dp) { 
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Notes APP", fontSize = 20.sp)
                        if (DB_TYPE.isNotEmpty()){
                            Icon(
                                imageVector = Icons.Default.ExitToApp,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    mViewModel.signOut {
                                        navController.navigate(NavRoute.Start.route){
                                            popUpTo(NavRoute.Start.route){
                                                inclusive = true
                                            }
                                        }
                                    }
                                })
                        }


                    }
                    

                }
                NotesNavHost(mViewModel, navController)

            }


        }
    }
}

