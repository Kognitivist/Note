package com.example.note

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.note.database.room.AppRoomDatabase
import com.example.note.database.room.repository.RoomRepository
import com.example.note.model.Note
import com.example.note.utils.REPOSITORY
import com.example.note.utils.TYPE_FIREBASE
import com.example.note.utils.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (application: Application): AndroidViewModel(application) {
    val context = application


    fun initDataBase(type: String, onSuccess: () -> Unit) {
        Log.d("Mylog", "MainViewModel initDataBase with type: $type")
        when(type){
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }
    fun addNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note){
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }
    fun readAllNotes() = REPOSITORY.readAll
}

class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application = application) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
    }
}