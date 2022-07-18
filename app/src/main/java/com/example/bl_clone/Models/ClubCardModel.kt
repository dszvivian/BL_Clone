package com.example.bl_clone.Models


data class ClubLists(
    var clubLists: ArrayList<ClubCardModel> = arrayListOf()
)


data class ClubCardModel constructor(
    var id:Int =0,
    var name:String= "",
    var icon:String="",
    var discNo: Int =0
    )


val clubsList = arrayListOf(
    ClubCardModel(1,"StartUP Club","https://cdn-icons-png.flaticon.com/512/609/609139.png",60),
    ClubCardModel(1,"BL Hangout","https://cdn-icons-png.flaticon.com/512/599/599527.png",60),
    ClubCardModel(1,"Hustle Club","https://cdn-icons.flaticon.com/png/512/2989/premium/2989420.png?token=exp=1657460073~hmac=ee9851081ca2b0958b3f2595c509b679",60),
    ClubCardModel(1,"Web3 Club","https://t3.ftcdn.net/jpg/04/91/81/30/240_F_491813026_3Dnny7uoKbaficbtNNHWDkJRnF7leaVx.jpg",60),
    ClubCardModel(1,"Finance Club","https://cdn-icons-png.flaticon.com/512/1140/1140214.png",60),
    ClubCardModel(1,"Quiz Club","https://cdn-icons-png.flaticon.com/512/3874/3874176.png",60),
)






