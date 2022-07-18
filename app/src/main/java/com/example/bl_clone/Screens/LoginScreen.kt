package com.example.bl_clone.Screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.bl_clone.Screen


@Composable
fun LoginScreen(navController: NavController) {

    var emailTextState by remember { mutableStateOf("") }
    var passwordTextState by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        color = MaterialTheme.colors.background
    ) {

        Box() {
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
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = emailTextState,
                    onValueChange = { emailTextState = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    label = {Text(text = "Enter Email")} ,
                    leadingIcon = {  AsyncImage(model = "https://img.icons8.com/ios-glyphs/344/new-post.png",
                        contentDescription = "",
                    contentScale = ContentScale.Fit,
                        modifier = Modifier.size(40.dp)
                        ) }

                )

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
                    leadingIcon = {  AsyncImage(model = "https://img.icons8.com/ios-filled/344/lock.png",
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(40.dp)
                    ) }

                )
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ){
                    Text(
                        text = "forgot password?",
                        style = MaterialTheme.typography.subtitle2
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Don't have a Account?Register",
                        style = MaterialTheme.typography.subtitle2 ,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.RegisterScreen.route)
                        }
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black ,
                        contentColor = Color.White
                        )
                ) {
                    Text(text = "Sign In")
                }


            }
        }


    }




}


