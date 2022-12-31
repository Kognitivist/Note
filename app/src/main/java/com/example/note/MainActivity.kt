package com.example.note

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.note.navigation.NotesNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
            Column {
                TopAppBar(
                backgroundColor = Color.Blue,
                contentColor = Color.White,
                elevation = 12.dp) { Text(text = "Notes APP", fontSize = 20.sp)
                }
                NotesNavHost(mViewModel)

            }


        }
    }
}

