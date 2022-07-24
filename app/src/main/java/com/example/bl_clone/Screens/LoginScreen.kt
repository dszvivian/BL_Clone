package com.example.bl_clone.Screens


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bl_clone.LoginViewModel
import com.example.bl_clone.Screen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun LoginScreen(
    navController: NavController ,
    loginViewModel: LoginViewModel = LoginViewModel()
    ) {

     val loginUiState = loginViewModel.loginUiState

    val isError =  loginViewModel.loginUiState.loginError != null



//    var emailTextState =
//    var passwordTextState ;


    val context = LocalContext.current



    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        color = MaterialTheme.colors.background
    ) {

        Box{
            Column {
                AsyncImage(model = "https://img.icons8.com/color/344/artstation.png",
                    contentDescription = "",
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Text(text = "Welcome Back!",
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Sign in to Continue",
                    style = MaterialTheme.typography.subtitle1
                )
                
                //username
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = loginUiState.userName,
                    onValueChange = { loginViewModel.onUserNameChange(it)},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    label = { Text(text = "Enter Email") },
                    leadingIcon = {
                        AsyncImage(model = "https://img.icons8.com/ios-glyphs/344/new-post.png",
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                )

                //password
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = loginUiState.password,
                    onValueChange = { loginViewModel.onPasswordChange(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    label = {
                        Text(text = "Enter Password",
                            style = MaterialTheme.typography.subtitle1
                        )
                    },
                    leadingIcon = {
                        AsyncImage(model = "https://img.icons8.com/ios-filled/344/lock.png",
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                )
                
                //forgot password --- does nothing
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = "forgot password?",
                        style = MaterialTheme.typography.subtitle2
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                // switch to Register page
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Don't have a Account?Register",
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.RegisterScreen.route)
                        }
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))
                Button(
                    onClick = {

                        loginViewModel.loginUser(context = context)

                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Sign In")
                }

                Spacer(modifier = Modifier.padding(10.dp))

                if(isError){
                    Text(
                        text = "Check the Fields Carefully",
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.Red
                    )
                }else{
                    Text(
                        text = "Good to go",
                        style = MaterialTheme.typography.subtitle2
                    )
                }


                // Circular ProgressBar
                if(loginUiState.isLoading){
                    CircularProgressIndicator()
                }


            }
        }


    }

    LaunchedEffect(key1 = loginViewModel.hasUser){
        if(loginViewModel.hasUser){
            navController.navigate(Screen.MainScreen.route)
        }
    }


}




