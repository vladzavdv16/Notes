package com.light.notes.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.light.notes.MainActivity
import com.light.notes.database.DatabaseRepository

lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_ID: String
lateinit var REF_DATABASE: DatabaseReference

const val TYPE_DATABASE = "type_database"

const val TYPE_ROOM = "type_room"

const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository

lateinit var APP_ACTIVITY: MainActivity

lateinit var EMAIL: String
lateinit var PASSWORD: String

const val ID_FIREBASE = "idFirebase"
const val NAME = "name"
const val TEXT = "text"