package com.example.note.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
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
import com.example.note.utils.Constants.Keys.DELETE
import com.example.note.utils.Constants.Keys.EDIT_NOTE
import com.example.note.utils.Constants.Keys.EMPTY
import com.example.note.utils.Constants.Keys.GO_BACK
import com.example.note.utils.Constants.Keys.NONE
import com.example.note.utils.Constants.Keys.NOTE_SUBTITLE
import com.example.note.utils.Constants.Keys.NOTE_TITLE
import com.example.note.utils.Constants.Keys.UPDATE
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Note (navController: NavHostController, viewModel: MainViewModel, noteId: String?){
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull{it.id == noteId?.toInt()} ?: com.example.note.model.Note(title = NONE, subTitle = NONE)
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val title = remember { mutableStateOf(EMPTY) }
    val subtitle = remember { mutableStateOf(EMPTY) }
    
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 8.dp,
        sheetShape = RoundedCornerShape(topEnd = 30.dp),
        sheetContent = {
            Surface{
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)) {
                    Text(text = EDIT_NOTE,
                        fontSize = 18.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = title.value,
                        onValueChange = {title.value = it},
                        label = { Text(text = NOTE_TITLE)},
                        isError = title.value.isEmpty())
                    OutlinedTextField(
                        value = subtitle.value,
                        onValueChange = {subtitle.value = it},
                        label = { Text(text = NOTE_SUBTITLE)},
                        isError = subtitle.value.isEmpty())
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            viewModel.updateNote(
                                com.example.note.model.Note(
                                    id = note.id,
                                    title = title.value,
                                    subTitle = subtitle.value)
                            ){
                                navController.navigate(NavRoute.Main.route)
                            }
                        }
                    ) {
                        Text(text = UPDATE)
                    }
                }
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()
            , horizontalAlignment = Alignment.CenterHorizontally
            , verticalArrangement = Arrangement.Center) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(30.dp)) {
                Column(modifier = Modifier.padding(vertical = 8.dp)
                    , horizontalAlignment = Alignment.CenterHorizontally
                    , verticalArrangement = Arrangement.Center)
                {
                    Text(text = note.title,
                        fontSize = 25.sp,
                        fontStyle = FontStyle.Italic
                    )
                    Text(text = note.subTitle)
                }

            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {
                Button(onClick = {
                    coroutineScope.launch {
                        title.value = note.title
                        subtitle.value = note.subTitle
                        bottomSheetState.show()
                    }
                }) {
                    Text(text = UPDATE)
                }
                Button(onClick = {
                    viewModel.deleteNote(note = note){
                        navController.navigate(NavRoute.Main.route)
                    }
                }) {
                    Text(text = DELETE)
                }
                Button(onClick = {
                    navController.navigate(NavRoute.Main.route)
                }) {
                    Text(text = GO_BACK)
                }
            }

        }
    }



}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevNoteScreen(){
    val context = LocalContext.current
    val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    Note(
        navController = rememberNavController(),
        viewModel = mViewModel,
        noteId = "1"
    )
}