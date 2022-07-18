package com.example.bl_clone.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bl_clone.Models.QuestionsList
import com.example.bl_clone.Screen


@Composable
fun AddPostScreen(navController: NavController) {

    var titleTextState by remember { mutableStateOf("") }
    var descTextState by remember { mutableStateOf("") }


    //top bar : textView + Post button
    //Row: text - dropdown to select club
    //Discussion Title
    //Discussion description (optional)

    Scaffold(topBar = { AddPostTopAppBar() }, backgroundColor = Color.Black) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
            color = Color.Black) {
            Column() {
                Row {
                    Text(text = "Posting to : ",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterVertically))
                    DropDownMenuCard()
                }

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 30.dp, bottom = 30.dp, start = 5.dp, end = 5.dp),
                    shape = RoundedCornerShape(4.dp)) {
                    TextField(value = titleTextState,
                        onValueChange = { titleTextState = it },
                        textStyle = MaterialTheme.typography.h6,
                        colors = TextFieldDefaults.textFieldColors(textColor = Color.White,
                            backgroundColor = Color.Black),
                        placeholder = {
                            Text(text = "What do you want to discuss about",
                                color = Color.White)
                        }
                    )
                }

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 30.dp, bottom = 30.dp, start = 5.dp, end = 5.dp),
                    shape = RoundedCornerShape(4.dp)) {
                    TextField(value = descTextState,
                        onValueChange = { descTextState = it },
                        textStyle = MaterialTheme.typography.subtitle1,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White,
                            backgroundColor = Color.Black
                        ),
                        placeholder = {
                            Text(text = "Your Discussion Subtitle",
                                color = Color.White)
                        }
                    )
                }


                Button(onClick = {

                    navController.navigate(Screen.DiscussionListScreen.route + "/${titleTextState}/${descTextState}")


                }, modifier = Modifier
                    .padding(40.dp)) {
                    Text(text = "Post")
                }


            }
        }


    }


}


@Composable
fun DropDownMenuCard() {

    val expanded = remember { mutableStateOf(false) }
    val listOfClubs = listOf("StartUp Club",
        "BL Hangout",
        "Hustle Club",
        "Web3 Club",
        "Finance Club",
        "Quiz Club")
    val currentValue = remember { mutableStateOf(listOfClubs[0]) }


    Surface(modifier = Modifier
        .wrapContentSize()
        .padding(5.dp), color = Color.Black) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)) {


            Card(shape = RoundedCornerShape(10.dp),
                backgroundColor = Color.Black,
                border = BorderStroke(1.dp, Color.White)) {
                Row(modifier = Modifier
                    .padding(3.dp)
                    .clickable {
                        expanded.value = !expanded.value
                    }
                )
                {
                    Text(text = currentValue.value, color = Color.White)
                    Image(imageVector = Icons.Default.ArrowDropDown, contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.White))
                    DropdownMenu(expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }) {

                        listOfClubs.forEach {

                            DropdownMenuItem(onClick = {
                                currentValue.value = it
                                expanded.value = false
                            }) {

                                Text(text = it, color = Color.Black)

                            }

                        }


                    }
                }
            }


        }
    }


}


@Composable
fun AddPostTopAppBar() {
    TopAppBar(
        title = {
            Text(text = "Start Discussion")
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        backgroundColor = Color.Black,
        contentColor = Color.White,
        elevation = 10.dp
    )
}
