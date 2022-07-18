package com.example.bl_clone.Models

data class QuestionCardModel constructor(
    var id :Int = 0 ,
    var title:String?  = "Title" ,
    var description:String? = "Description",
    var userPicUrl : String  = "https://cdn-icons.flaticon.com/png/512/1144/premium/1144709.png?token=exp=1657467739~hmac=7e392c52e4b190502aa760a812e9f6cb",
    var userName : String  = "Jeremiah Dame"

)
