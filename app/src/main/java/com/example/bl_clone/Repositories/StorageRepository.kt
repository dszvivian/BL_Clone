package com.example.bl_clone.Repositories

import com.example.bl_clone.Models.QuestionCardModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


const val QUESTION_LIST_REF = "questions"  // reference to collection


class StorageRepository() {

    val currentUser = Firebase.auth.currentUser

    fun hasUser():Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String? = Firebase.auth.currentUser?.uid


    suspend fun addQuestion(
        question :QuestionCardModel,
        OnComplete : (Boolean) -> Unit
    ) = withContext(Dispatchers.IO){

            try {
                val collection = Firebase.firestore.collection(QUESTION_LIST_REF)
                    .add(question).await()

                withContext(Dispatchers.Main){
                    OnComplete.invoke(true)
                }
            }catch (e:Exception) {
                withContext(Dispatchers.Main){
                    OnComplete.invoke(true)
            }
        }






    }



}