package com.light.notes.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreference {

    private const val INIT_USER = "initUser"

    private const val TYPE_DB = "typeDB"

    private const val NAME_PREF = "preference"

    private lateinit var preferences: SharedPreferences


    fun getPreference(context: Context): SharedPreferences {
        preferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
        return preferences
    }

    fun setInitUser(init: Boolean) {
        preferences.edit()
            .putBoolean(NAME_PREF, init)
            .apply()
    }

    fun setTypeDB(type: String) {
        preferences.edit().putString(TYPE_DB, type).apply()
    }

    fun getInitUser(): Boolean {
        return preferences.getBoolean(INIT_USER, false)
    }

    fun getTypeDb(): String {
        return preferences.getString(TYPE_DB, TYPE_ROOM).toString()
    }
}