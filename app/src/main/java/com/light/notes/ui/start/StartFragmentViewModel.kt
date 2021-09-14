package com.light.notes.ui.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.light.notes.database.room.AppRoomDatabase
import com.light.notes.database.room.AppRoomRepository
import com.light.notes.utils.REPOSITORY
import com.light.notes.utils.TYPE_ROOM

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
        }
    }
}