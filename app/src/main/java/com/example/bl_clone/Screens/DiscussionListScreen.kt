package com.example.bl_clone.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bl_clone.Models.QuestionCardModel
import com.example.bl_clone.Screen
import com.example.bl_clone.rColors
import kotlin.random.Random


@Composable
fun DiscussionListScreen(navController: NavController, title: String?, desc: String?) {


    var questionList by remember {  mutableStateOf(mutableListOf<QuestionCardModel>())  }


    questionList.add(QuestionCardModel(title = title , description = desc ))



    Scaffold(backgroundColor = Color.Black, floatingActionButton = { FabDiscussionList(navController) }) {
        Column() {

            Text(text = "Discussions", color = Color.White, style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp, end = 5.dp, bottom = 5.dp)
            )


            DiscussionListCard(navController,
                QuestionCardModel(title = title , description = desc))





        }
    }


}


@Composable
fun DiscussionListCard(navController: NavController , model: QuestionCardModel) {

    val random = remember { Random.nextInt(0, rColors.size)}

/*
    val profileImageUrl =
        "https://cdn-icons.flaticon.com/png/512/1144/premium/1144709.png?token=exp=1657467739~hmac=7e392c52e4b190502aa760a812e9f6cb"
*/

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp),
        backgroundColor = rColors[random],
        elevation = 5.dp
    )
    {

        Column(Modifier.padding(20.dp)) {

            Row {
                Card(
                    shape = CircleShape,
                    backgroundColor = Color.Black,
                    modifier = Modifier
                        .size(60.dp)
                ) {
                    //Image
                    AsyncImage(
                        model = model.userPicUrl,
                        contentDescription = "Club profile Icon",
                        contentScale = ContentScale.Fit,
                    )

                }

                //person name
                Text(text = model.userName,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp)

                )


            }
            //heading
            if (model.title != null) {
                Text(text = model.title!!, style = MaterialTheme.typography.h6, color = Color.White,
                    maxLines = 1, modifier = Modifier.padding(top = 5.dp)
                )
            }

            if (model.description != null) {
                Text(text = model.description!!,
                    style = MaterialTheme.typography.subtitle2,
                    color = Color(1f, 1f, 1f, 0.6f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }


            Row(modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.End)) {

                Text(
                    text = "Start Discussion",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White,
                )

                Image(imageVector = Icons.Default.ArrowForward, contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.navigate(Screen.DiscussionScreen.route + "/${model.title}/${model.description}")
                        }
                )
            }

        }

    }
}


@Composable
private fun FabDiscussionList(navController: NavController) {
    FloatingActionButton(
        onClick = {
                  navController.navigate(Screen.AddPostScreen.route)
                  },
        shape = CircleShape,
        backgroundColor = Color(0f, 0.9f, 1f, 1f)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "")
    }
}


