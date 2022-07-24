package com.example.bl_clone

sealed class Screen(val route :String){
    object MainScreen : Screen("dest_mainScreen")
    object DiscussionListScreen : Screen("dest_discussionListScreen")
    object DiscussionScreen : Screen("dest_discussionScreen")
    object AddPostScreen : Screen("dest_addPostScreen")
    object LoginScreen : Screen("dest_loginScreen")
    object RegisterScreen : Screen("dest_registerScreen")
    object UserDetailsUpdateScreen : Screen("dest_userDetailsUpdateScreen")
}
