package com.example.bl_clone.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bl_clone.rColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun DiscussionScreen(title: String?, desc: String?){

    Surface(color = Color.Black) {
        Column(Modifier.fillMaxSize()) {

                    HeaderPStatemnet(title,desc)
                    MessagingDiscussionCard()
        }
    }

}



@Composable
fun MessagingDiscussionCard() {

    var textState by remember{ mutableStateOf("")}  //textSte of input Text

    var messageListState by remember {  mutableStateOf(listOf("","" ))  }



    Column(modifier = Modifier
        .padding(20.dp)
        ) {

        var stateLazycol  = rememberLazyListState()
        LazyColumn(modifier = Modifier
            .fillMaxHeight(0.8f)
            .wrapContentWidth()
            ,
            state =  stateLazycol ,

            ){

            item{
                MessageCard(message = "Hi this is Jeremiah\nShare your opinions below ?", isSender = true)
            }
            items(messageListState.size) { index ->
                MessageCard(message = messageListState[index], isSender = false)
            }
        }
        
        

        Row(verticalAlignment = Alignment.Bottom ,
        modifier = Modifier
            .wrapContentHeight()
            .padding(10.dp)
            ){

                TextField(value = textState,
                onValueChange = {textState = it} ,
                label = { Text(text = "Enter Message...." , modifier = Modifier.align(Alignment.CenterVertically) )},
                colors = TextFieldDefaults
                    .textFieldColors(
                        textColor = Color(1f,1f,1f,1f),
                        unfocusedLabelColor = Color(1f,1f,1f,1f)
                    ),
                modifier = Modifier.fillMaxWidth(0.8f),
            )

            Button(onClick = {
                             messageListState += listOf(textState)
                            textState = ""

                //todo do scroll view in a coroutine

                CoroutineScope(Dispatchers.Main).launch{
                    stateLazycol.scrollToItem(messageListState.size -1)
                }



            } ,Modifier.padding(5.dp)) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "" , )
            }  // button
        }
    }

}



@Composable
fun MessageCard(message:String , isSender :Boolean){
    val random = Random.nextInt(0, rColors.size)

    Column(horizontalAlignment = if (isSender) Alignment.Start else Alignment.End) {
        Card(shape = RoundedCornerShape(
            topStart = if(isSender) 0.dp else 20.dp ,
            topEnd = 20.dp , bottomEnd = 20.dp
            , bottomStart = if(!isSender) 0.dp else 20.dp
        ) ,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .wrapContentHeight()
                .padding(start = 5.dp, top = 5.dp, bottom = 5.dp, end = 5.dp)
            ,
            backgroundColor = Color.White
        ) {

            Column{
                Text(text = "Jeremiah Dame",style = MaterialTheme.typography.subtitle1 , color = rColors[random])
                Text(text = message,
                    color = Color.Black,
                    style = MaterialTheme.typography.subtitle2
                )
            }

        }
    }



}


//problem Statement
@Composable
fun HeaderPStatemnet(title:String? , desc:String?){
    val random = Random.nextInt(0, rColors.size)

    val profileImageUrl = "https://cdn-icons.flaticon.com/png/512/1144/premium/1144709.png?token=exp=1657467739~hmac=7e392c52e4b190502aa760a812e9f6cb"

    Card(
        shape = RoundedCornerShape(20.dp) ,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp)
        ,
        backgroundColor = remember { rColors[random] },
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
                        model = profileImageUrl,
                        contentDescription = "Club profile Icon",
                        contentScale = ContentScale.Fit,
                    )

                }

                //person name
                Text(text = "Jeremiah Dame",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp)

                )


            }
            //heading
            if (title != null) {
                Text(text = title,
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    maxLines = 1,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            if (desc != null) {
                Text(text = desc,
                    style = MaterialTheme.typography.subtitle2,
                    color = Color(1f, 1f, 1f, 0.6f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }

        }}
}




