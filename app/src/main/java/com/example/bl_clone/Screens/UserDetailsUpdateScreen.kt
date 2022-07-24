package com.example.bl_clone.Screens

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.bl_clone.Repositories.AuthRepository
import com.example.bl_clone.Screen
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

@Composable
fun UserDetailsUpdateScreen(navController: NavController) {

    var usernameTextState by remember { mutableStateOf("") }
    var profileUrl by remember{ mutableStateOf("https://img.icons8.com/ios/2x/user.png")}

   /* //firebase declarations
    val auth = Firebase.auth*/



        //current userState
    var userState: String

   /* val userCurrent = auth.currentUser

        if (userCurrent == null) {
            userState = "Not Logged in"
        } else {
            userState = "User Logged in"
            usernameTextState = userCurrent.displayName!!
            profileUrl = userCurrent.photoUrl.toString()
        }*/


        val context = LocalContext.current

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.Black
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(20.dp)
            ) {

                Column {

                    //profile image
                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    {
                        Card(
                            shape = CircleShape,
                            modifier = Modifier
                                .size(180.dp)
                        ) {
                            AsyncImage(model = profileUrl,
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }


                    Spacer(modifier = Modifier.padding(10.dp))

                    //username
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        value = usernameTextState,
                        onValueChange = { usernameTextState = it },
                        colors = TextFieldDefaults
                            .textFieldColors(
                                textColor = Color(1f, 1f, 1f, 1f),
                                unfocusedLabelColor = Color(1f, 1f, 1f, 1f)
                            ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        label = { Text(text = "Enter Username...") },
                        leadingIcon = {
                            AsyncImage(model = profileUrl,
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(40.dp),
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                        }

                    )

                    Spacer(modifier = Modifier.padding(20.dp))

                    //Update button
                    Button(onClick = {






                    },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Update Profile")
                    }

                    Spacer(modifier = Modifier.padding(20.dp))

                    Text(text = "userState",
                        color = Color.White,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )


                    Button(onClick = {


                        Firebase.auth.signOut()



                        navController.navigate(Screen.LoginScreen.route)





                    },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Log out")
                    }




                }


            }




    }


}