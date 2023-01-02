package com.example.note.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.note.MainViewModel
import com.example.note.MainViewModelFactory
import com.example.note.navigation.NavRoute
import com.example.note.utils.*
import com.example.note.utils.Constants.Keys.FIREBASE_DATABASE
import com.example.note.utils.Constants.Keys.ROOM_DATABASE
import com.example.note.utils.Constants.Keys.WHAT_WILL_BE_USED
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Start (navController: NavHostController, viewModel: MainViewModel){
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val login = remember { mutableStateOf(Constants.Keys.EMPTY) }
    val password = remember { mutableStateOf(Constants.Keys.EMPTY) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 8.dp,
        sheetShape = RoundedCornerShape(topEnd = 30.dp),
        sheetContent = {
            Surface{
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)) {
                    Text(text = Constants.Keys.LOG_IN,
                        fontSize = 18.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = login.value,
                        onValueChange = {login.value = it},
                        label = { Text(text = Constants.Keys.LOG_IN_TEXT)},
                        isError = login.value.isEmpty())
                    OutlinedTextField(
                        value = password.value,
                        onValueChange = {password.value = it},
                        label = { Text(text = Constants.Keys.PASSWORD_TEXT)},
                        isError = password.value.isEmpty())
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            LOGIN = login.value
                            PASSWORD = password.value
                            viewModel.initDataBase(TYPE_FIREBASE){
                                DB_TYPE = TYPE_FIREBASE
                                navController.navigate(route = NavRoute.Main.route)
                            }
                        },
                        enabled = login.value.isNotEmpty() && password.value.isNotEmpty()
                    ) {
                        Text(text = Constants.Keys.SIGN_IN)
                    }
                }
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = WHAT_WILL_BE_USED)
            Button(
                onClick = {
                    viewModel.initDataBase(TYPE_ROOM) {
                        DB_TYPE = TYPE_ROOM
                        navController.navigate(route = NavRoute.Main.route)
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = ROOM_DATABASE)
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        bottomSheetState.show()
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = FIREBASE_DATABASE)
            }
        }
    }




}
@Preview(showBackground = true)
@Composable
fun PrevStartScreen(){
    val context = LocalContext.current
    val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    Start(navController = rememberNavController(), viewModel = mViewModel)
}