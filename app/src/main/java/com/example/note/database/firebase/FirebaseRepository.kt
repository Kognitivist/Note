package com.example.note.database.firebase

import androidx.lifecycle.LiveData
import com.example.note.database.DataBaseRepository
import com.example.note.model.Note
import com.example.note.utils.LOGIN
import com.example.note.utils.PASSWORD
import com.google.firebase.auth.FirebaseAuth

class FirebaseRepository : DataBaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
    override val readAll: LiveData<List<Note>>
        get() = TODO("Not yet implemented")

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        mAuth.signOut()
    }

    override fun connectToDataBase(onSuccess: ()-> Unit, onFail: (String)-> Unit) {

        mAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { it.message.toString() } }
    }
}