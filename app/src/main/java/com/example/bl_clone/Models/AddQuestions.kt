package com.example.bl_clone.Models

import androidx.compose.runtime.*


@Composable
fun QuestionsList(){
    var questionList by remember {  mutableStateOf(listOf<QuestionCardModel>())  }

    fun addQuestions(title:String , desc:String){
        questionList += listOf(QuestionCardModel(title = title , description = desc ))
    }




}