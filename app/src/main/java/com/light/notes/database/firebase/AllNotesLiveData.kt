package com.light.notes.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.light.notes.model.AppNote
import com.light.notes.utils.REF_DATABASE

class AllNotesLiveData : LiveData<List<AppNote>>() {


    private val listener = object : ValueEventListener {
        override fun onDataChange(p0: DataSnapshot) {
            value = p0.children.map {
                it.getValue(AppNote::class.java) ?: AppNote()
            }
        }

        override fun onCancelled(p0: DatabaseError) {

        }

    }

    override fun onActive() {
        super.onActive()

        REF_DATABASE.addValueEventListener(listener)
    }

    override fun onInactive() {
        super.onInactive()

        REF_DATABASE.removeEventListener(listener)
    }
}