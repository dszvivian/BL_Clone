package com.example.bl_clone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bl_clone.Screens.*
import com.example.bl_clone.ui.theme.BL_cloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val loginViewModel = viewModel(modelClass = LoginViewModel::class.java)

            BL_cloneTheme {
//                DiscussionScreen("hello","world")
                BlCloneApp(loginViewModel)
            }
        }
    }
}

@Composable
fun BlCloneApp(viewModel: LoginViewModel ) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {

        composable(route = Screen.LoginScreen.route){
            LoginScreen(navController,viewModel)
        }

        composable(route = Screen.RegisterScreen.route){
            RegisterScreen(navController,viewModel)
        }


        composable(route = Screen.MainScreen.route) {
            BlMainScreen(navController = navController)
        }
        composable(route = Screen.DiscussionListScreen.route + "/{title}/{desc}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = "Title"
                    nullable = true
                },
                navArgument("desc") {
                    type = NavType.StringType
                    defaultValue = "Description"
                    nullable = true
                }
            )
        ) { entry ->
            DiscussionListScreen(
                navController = navController,
                title = entry.arguments?.getString("title"),
                desc = entry.arguments?.getString("desc"))
        }
        composable(
            route = Screen.DiscussionScreen.route + "/{title}/{desc}",

            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = "Title"
                    nullable = true
                },
                navArgument("desc") {
                    type = NavType.StringType
                    defaultValue = "Description"
                    nullable = true
                }

            ))
            {entry ->
            DiscussionScreen(
                title = entry.arguments?.getString("title"),
                desc = entry.arguments?.getString("desc")
            )
            //todo bring title and description from discList Screen through arguments
        }
        composable(route = Screen.AddPostScreen.route) {
            AddPostScreen(navController = navController)
        }
        composable(route = Screen.UserDetailsUpdateScreen.route){
            UserDetailsUpdateScreen(navController)
        }
    }


}

