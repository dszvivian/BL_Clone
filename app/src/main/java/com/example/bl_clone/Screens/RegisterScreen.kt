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
import com.example.bl_clone.Screen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

@Composable
fun RegisterScreen(navController: NavHostController) {

    var emailTextState by remember { mutableStateOf("") }
    var passwordTextState by remember { mutableStateOf("") }
    var usernameTextState by remember { mutableStateOf("") }
    var confirmPasswordTextState by remember { mutableStateOf("") }


    var userLoggedState = remember { mutableStateOf("Not logged in ")}
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()




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

                //username
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = usernameTextState,
                    onValueChange = { usernameTextState = it },
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

                )

                //email
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = emailTextState,
                    onValueChange = { emailTextState = it },
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
                    value = passwordTextState,
                    onValueChange = { passwordTextState = it },
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
                    value = confirmPasswordTextState,
                    onValueChange = { confirmPasswordTextState = it },
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
                        /*TODO register user in firebase*/



                        // region implements fireBase authentication

                        val auth: FirebaseAuth = FirebaseAuth.getInstance()

                        if(emailTextState.isNotEmpty() && passwordTextState.isNotEmpty()){
                            coroutineScope.launch {

                                withContext(Dispatchers.IO){
                                    try {
                                        auth.createUserWithEmailAndPassword(emailTextState , passwordTextState).addOnCompleteListener{task ->
                                            if(task.isComplete){
                                                userLoggedState.value = "User Logged in"
                                                navController.navigate(Screen.MainScreen.route)
                                            }
                                            else{
                                                userLoggedState.value = "User Not Logged in"
                                                navController.navigate(Screen.MainScreen.route)
                                            }
                                        }
                                    }
                                    catch (e: Exception){
                                        Toast.makeText(context , e.message , Toast.LENGTH_SHORT).show()
                                    }
                                }

                            }
                        }

                        // endregion implements fireBase authentication






                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Register")
                }

                Text(
                    text = userLoggedState.value,
                    style = MaterialTheme.typography.subtitle2
                )


            }
        }


    }


}