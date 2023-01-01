package com.example.note.database

import androidx.lifecycle.LiveData
import com.example.note.model.Note

interface DataBaseRepository {
    val readAll: LiveData<List<Note>>

    suspend fun create(note: Note, onSuccess: ()-> Unit)
    suspend fun update(note: Note, onSuccess: ()-> Unit)
    suspend fun delete(note: Note, onSuccess: ()-> Unit)

    fun signOut(){

    }

    fun connectToDataBase(onSuccess: ()-> Unit, onFail: (String)-> Unit){

    }


}