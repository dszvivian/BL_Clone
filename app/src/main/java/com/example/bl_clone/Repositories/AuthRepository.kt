package com.example.bl_clone.Repositories


import android.service.autofill.OnClickAction
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepository {

    val currentUser: FirebaseUser? = Firebase.auth.currentUser

    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.uid.orEmpty()


    suspend fun createUser(
        email: String,
        password: String,
        onComplete: (Boolean) -> Unit,
    ) = withContext(Dispatchers.IO) {

        Firebase.auth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isComplete) {
                    onComplete.invoke(true)
                } else {
                    onComplete(false)
                }
            }.await()
    }


    suspend fun loginUser(
        email: String,
        password: String,
        onComplete: (Boolean) -> Unit,
    ) = withContext(Dispatchers.IO) {

        Firebase.auth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isComplete) {
                    onComplete.invoke(true)
                } else {
                    onComplete(false)
                }
            }.await()
    }



}