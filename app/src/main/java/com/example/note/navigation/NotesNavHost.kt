package com.example.note.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.note.MainViewModel
import com.example.note.screens.add
import com.example.note.screens.main
import com.example.note.screens.Note
import com.example.note.screens.start
import com.example.note.utils.Constants.Keys.ID

import com.example.note.utils.Constants.Screens.ADD_SCREEN
import com.example.note.utils.Constants.Screens.MAIN_SCREEN
import com.example.note.utils.Constants.Screens.NOTE_SCREEN
import com.example.note.utils.Constants.Screens.START_SCREEN

sealed class NavRoute (val route: String){
    object Start: NavRoute (START_SCREEN)
    object Main: NavRoute (MAIN_SCREEN)
    object Add: NavRoute (ADD_SCREEN)
    object Note: NavRoute (NOTE_SCREEN)
}

@Composable
fun NotesNavHost(mViewModel: MainViewModel){
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = NavRoute.Start.route){
        composable(NavRoute.Start.route){ start(navController, viewModel = mViewModel) }
        composable(NavRoute.Main.route){ main(navController, viewModel = mViewModel) }
        composable(NavRoute.Add.route){ add(navController, viewModel = mViewModel) }
        composable(NavRoute.Note.route + "/{$ID}"){ backStackEntry ->
            Note(navController, viewModel = mViewModel, noteId = backStackEntry.arguments?.getString(ID)) }
    }



}