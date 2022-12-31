package com.example.note.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.note.model.Note
import com.example.note.navigation.NavRoute
import com.example.note.utils.Constants.Keys.ADD_NEW_NOTE
import com.example.note.utils.Constants.Keys.ADD_NOTE
import com.example.note.utils.Constants.Keys.NOTE_SUBTITLE
import com.example.note.utils.Constants.Keys.NOTE_TITLE


@Composable
fun add (navController: NavHostController, viewModel: MainViewModel){
    val title = remember { mutableStateOf("") }
    val subTitle = remember { mutableStateOf("") }
    var isButtonEnabled = remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()
        , horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Center) {
        Text(text = ADD_NEW_NOTE, modifier = Modifier.padding(vertical = 10.dp))
        OutlinedTextField(value = title.value,
            onValueChange = {
                title.value = it
                isButtonEnabled.value = title.value.isNotEmpty() && subTitle.value.isNotEmpty()
            },
            label = { Text(text = NOTE_TITLE)},
            isError = title.value.isEmpty())

        OutlinedTextField(value = subTitle.value,
            onValueChange = {
                subTitle.value = it
                isButtonEnabled.value = title.value.isNotEmpty() && subTitle.value.isNotEmpty()
            },
            label = { Text(text = NOTE_SUBTITLE)},
            isError = subTitle.value.isEmpty())
        Button(modifier = Modifier.padding(top = 16.dp),
            enabled = isButtonEnabled.value,
            onClick = {
                viewModel.addNote(note = Note(title = title.value, subTitle = subTitle.value)){
                    navController.navigate(route = NavRoute.Main.route)
                }

            })
        {
            Text(text = ADD_NOTE)
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun prevAddScreen(){
    val context = LocalContext.current
    val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    add(navController = rememberNavController(), viewModel = mViewModel)
}