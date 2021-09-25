package com.light.notes.database

import androidx.lifecycle.LiveData
import com.light.notes.model.AppNote

interface DatabaseRepository {

    val allNotes: LiveData<List<AppNote>>
    suspend fun insert(note: AppNote, onSuccess: () -> Unit)
    suspend fun delete(note: AppNote, onSuccess: () -> Unit)

    fun connectToDatabase(onSuccess: () -> Unit, onFailure: (String) -> Unit){}

    fun signOut(){}
}