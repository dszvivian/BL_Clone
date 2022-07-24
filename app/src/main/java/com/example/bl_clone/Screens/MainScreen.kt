package com.example.bl_clone.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bl_clone.Models.ClubCardModel
import com.example.bl_clone.Models.clubsList
import com.example.bl_clone.Screen

@Composable
fun BlMainScreen(navController: NavController, clubs: List<ClubCardModel> = clubsList) {
    Surface(
        color = Color.Black,
        modifier = Modifier.fillMaxSize()

    ) {

        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(5.dp)) {


            Box(modifier = Modifier.fillMaxWidth())
             {


                    Text(text = "My Clubs",
                        color = Color.White,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )

                    IconButton(
                        onClick = {
                                  navController.navigate(Screen.UserDetailsUpdateScreen.route)
                        },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        AsyncImage(model = "https://img.icons8.com/ios/2x/user.png",
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(5.dp) ,
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }




            }



            LazyColumn {
                items(clubs) { club ->
                    ClubCard(club = club, navController)
                }
            }


        }

    }


}

@Composable
fun ClubCard(club: ClubCardModel, navController: NavController) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .padding(20.dp),
        backgroundColor = Color.Black)
    {

        Row {
            Card(
                shape = CircleShape,
                backgroundColor = Color.Black,
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.CenterVertically)
            ) {
                //Image
                AsyncImage(model = club.icon,
                    contentDescription = "Club profile Icon",
                    contentScale = ContentScale.Fit)

            }
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.8f)) {
                //Club name : Text
                Text(text = club.name,
                    style = MaterialTheme.typography.h6,
                    color = Color.White
                )
                //Discussion List
                Text(
                    text = "${club.discNo} discussions",
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.White
                )
            }

            Image(imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        navController.navigate(Screen.DiscussionListScreen.route + "/title/description")
                    },
                colorFilter = ColorFilter.tint(Color.White)
            )


        }

    }

}