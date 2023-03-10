package com.example.note.database.firebase

import androidx.lifecycle.LiveData
import com.example.note.model.Note
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AllNotesLiveData: LiveData<List<Note>>() {
    private val mAuth = FirebaseAuth.getInstance()
    private val dataBase = Firebase.database.reference.
    child(mAuth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val notes = mutableListOf<Note>()
            snapshot.children.map {
                notes.add(it.getValue(Note::class.java) ?: Note())
            }
            value = notes
        }

        override fun onCancelled(error: DatabaseError) {
        }

    }

    override fun onActive() {
        dataBase.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        dataBase.removeEventListener(listener)
        super.onInactive()
    }
}