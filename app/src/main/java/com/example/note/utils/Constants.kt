package com.example.note.utils

import com.example.note.database.DataBaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DataBaseRepository
lateinit var LOGIN: String
lateinit var PASSWORD: String

object Constants {
    object Keys{
        const val NOTE_DATABASE = "note_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "Add new Note"
        const val NOTE_TITLE = "Note Title"
        const val NOTE_SUBTITLE =  "Note SubTitle"
        const val ADD_NOTE = "Add Note"
        const val WHAT_WILL_BE_USED = "What will be used?"
        const val ROOM_DATABASE = "Room database"
        const val FIREBASE_DATABASE = "Firebase database"
        const val ID = "Id"
        const val NONE = "None"
        const val UPDATE ="Update"
        const val DELETE ="Delete"
        const val GO_BACK ="Go_back"
        const val EDIT_NOTE = "Edit note"
        const val EMPTY = ""
        const val SIGN_IN = "Sign in"
        const val LOG_IN = "Log in"
        const val LOG_IN_TEXT = "Login"
        const val PASSWORD_TEXT = "Password"








    }

    object Screens{
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"
    }
}