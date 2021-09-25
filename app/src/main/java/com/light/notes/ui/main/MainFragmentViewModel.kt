package com.light.notes.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.light.notes.utils.REPOSITORY

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes = REPOSITORY.allNotes
    fun signOut(){
        REPOSITORY.signOut()
    }

}