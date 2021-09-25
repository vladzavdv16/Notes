package com.light.notes.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.light.notes.database.DatabaseRepository
import com.light.notes.model.AppNote
import com.light.notes.utils.*

class AppFirebaseRepository : DatabaseRepository {

    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    init {
        AUTH = FirebaseAuth.getInstance()
    }

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val idNote = REF_DATABASE.push().key.toString()
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text

        REF_DATABASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }

    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        REF_DATABASE.child(note.idFirebase).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { it.message.toString() }
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFailure: (String) -> Unit) {

        if (AppPreference.getInitUser()) {
            initRefs()
        } else {
            AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnSuccessListener {
                    initRefs()
                    onSuccess()
                }
                .addOnFailureListener {
                    AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { onFailure(it.message.toString()) }
                }
        }
    }

    private fun initRefs() {
        CURRENT_ID = AUTH.currentUser?.uid.toString()

        REF_DATABASE = FirebaseDatabase.getInstance().reference
            .child(CURRENT_ID)
    }

    override fun signOut() {
        AUTH.signOut()
    }
}