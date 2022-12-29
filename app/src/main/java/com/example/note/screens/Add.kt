package com.example.note.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.note.navigation.NavRoute


@Composable
fun add (navController: NavHostController){
    val title = remember { mutableStateOf("") }
    val subTitle = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()
        , horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Center) {
        Text(text = "Add new Note", modifier = Modifier.padding(vertical = 10.dp))
        OutlinedTextField(value = title.value,
            onValueChange = { title.value = it },
            label = { Text(text = "Note Title")})

        OutlinedTextField(value = subTitle.value,
            onValueChange = { subTitle.value = it },
            label = { Text(text = "Note SubTitle")})
        Button(modifier = Modifier.padding(top = 16.dp),
            onClick = {
                navController.navigate(route = NavRoute.Main.route)
            })
        {
            Text(text = "Add note")
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun prevAddScreen(){
    add(navController = rememberNavController())
}