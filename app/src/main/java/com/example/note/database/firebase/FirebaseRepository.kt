package com.example.note.database.firebase

import androidx.lifecycle.LiveData
import com.example.note.database.DataBaseRepository
import com.example.note.model.Note
import com.example.note.utils.Constants
import com.example.note.utils.Constants.Keys.NOTE_SUBTITLE
import com.example.note.utils.Constants.Keys.NOTE_TITLE
import com.example.note.utils.FIREBASE_ID
import com.example.note.utils.LOGIN
import com.example.note.utils.PASSWORD
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepository : DataBaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
    override val readAll: LiveData<List<Note>> = AllNotesLiveData()
    private val dataBase = Firebase.database.reference.
    child(mAuth.currentUser?.uid.toString())


    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        val noteId = dataBase.push().key.toString()
        val mapNotes = hashMapOf<String,Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[NOTE_TITLE] = note.title
        mapNotes[NOTE_SUBTITLE] = note.subTitle

        dataBase.child(noteId).updateChildren(mapNotes)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {  }
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