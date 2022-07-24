package com.example.bl_clone.Screens

import android.widget.Toast
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.bl_clone.LoginViewModel
import com.example.bl_clone.Screen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.math.sign

@Composable
fun RegisterScreen(
    navController: NavHostController ,
    signUpViewModel: LoginViewModel = LoginViewModel()) {


    val signUpUiState = signUpViewModel.loginUiState

    val isError = signUpUiState.signUpError != null
    val context = LocalContext.current





    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        color = MaterialTheme.colors.background
    ) {


        Box(contentAlignment = Alignment.CenterStart) {
            Column {


                Text(text = "Almost there!",
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "We are excited to see you here...!",
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.padding(6.dp))

                //todo username
                /*TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = signUpUiState.signUpUserName,
                    onValueChange = { signUpViewModel.onSignUpUserNameChange(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    label = { Text(text = "Enter Username") },
                    leadingIcon = {
                        AsyncImage(model = "https://img.icons8.com/material-rounded/2x/user.png",
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                )*/

                //email -- username for now
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = signUpUiState.signUpUserName,
                    onValueChange = { signUpViewModel.onSignUpUserNameChange(it) },
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
                    value = signUpUiState.signUpPassword,
                    onValueChange = { signUpViewModel.onSignUpPasswordChange(it) },
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

                //confirm password
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = signUpUiState.confirmPassword,
                    onValueChange = { signUpViewModel.onSignUpConfirmPasswordChange(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    label = {
                        Text(text = "Confirm Password",
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


                Spacer(modifier = Modifier.padding(20.dp))
                Button(
                    onClick = {

                    signUpViewModel.createUser(context)


                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Register")
                }

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


            }
        }


    }


    LaunchedEffect(key1 = signUpViewModel.hasUser ){
        if(signUpViewModel.hasUser){
            navController.navigate(Screen.MainScreen.route)
        }
    }


}