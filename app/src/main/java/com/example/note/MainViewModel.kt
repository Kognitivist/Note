package com.example.note

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.note.model.Note
import com.example.note.utils.TYPE_FIREBASE
import com.example.note.utils.TYPE_ROOM

class MainViewModel (application: Application): AndroidViewModel(application) {
    //???
    val readTest: MutableLiveData<List<Note>> by lazy{
        MutableLiveData<List<Note>>()
    }
    val dbType: MutableLiveData<String> by lazy {
        MutableLiveData<String>(TYPE_ROOM)
    }

    init {
        readTest.value =
            when(dbType.value){
                TYPE_ROOM -> {
                    listOf<Note>(
                        Note(title = "Title 1", subTitle = "SubTitle 1"),
                        Note(title = "Title 2", subTitle = "SubTitle 2"),
                        Note(title = "Title 3", subTitle = "SubTitle 3"),
                        Note(title = "Title 4", subTitle = "SubTitle 4")
                    )
                }
                TYPE_FIREBASE -> listOf()
                else -> listOf()
            }
    }

    fun initDataBase(type: String) {
        dbType.value = type
        Log.d("Mylog", "MainViewModel initDataBase with type: $type")
    }
}

class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application = application) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
    }
}