package com.example.note.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.note.screens.add
import com.example.note.screens.main
import com.example.note.screens.note
import com.example.note.screens.start

sealed class NavRoute (val route: String){
    object Start: NavRoute ("start_screen")
    object Main: NavRoute ("main_screen")
    object Add: NavRoute ("add_screen")
    object Note: NavRoute ("note_screen")
}

@Composable
fun notesNavHost(){
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = NavRoute.Start.route){
        composable(NavRoute.Start.route){ start(navController) }
        composable(NavRoute.Main.route){ main(navController) }
        composable(NavRoute.Add.route){ add(navController) }
        composable(NavRoute.Note.route){ note(navController) }
    }



}