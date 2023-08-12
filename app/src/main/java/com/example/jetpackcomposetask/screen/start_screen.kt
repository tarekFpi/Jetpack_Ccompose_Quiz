package com.example.jetpackcomposetask.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpackcomposetask.R
import com.example.jetpackcomposetask.view.ui.theme.*
import com.example.starbucks_ui.screen.home
import com.example.starbucks_ui.screen.start

@Composable
fun StartScreen(navHostController: NavHostController){


    Box (
        modifier = Modifier
            .background(Gray500),
        contentAlignment = Alignment.Center
     ){

     Column(modifier = Modifier
       .fillMaxSize(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = CenterHorizontally
     ) {

         Card(shape = RoundedCornerShape(14.dp),
             modifier = Modifier
                 .width(300.dp)
                 .background(DarkGray)
                 ) {

             Column{

                 Box(
                     modifier = Modifier
                         .padding(8.dp)
                         .background(
                             LightRed_1, RoundedCornerShape(
                                 bottomStart = 14.dp, bottomEnd = 14.dp
                             )
                         )
                         .fillMaxWidth()
                         .height(200.dp), contentAlignment = Alignment.Center
                 ) {

                     Image(
                         painter = painterResource(id = R.drawable.logo),
                         contentDescription = "",
                         modifier = Modifier.size(100.dp)
                     )

                 }

                 Spacer(modifier = Modifier.height(8.dp))

                 titleText()
             }

         }

         Spacer(modifier = Modifier.height(14.dp))

         hightScoreText()

         Spacer(modifier = Modifier.height(14.dp))

         Button(onClick = {

         navHostController.navigate(home)
         },
             modifier = Modifier.width(300.dp),
             colors = ButtonDefaults.buttonColors(
                 contentColor = Color.White,
                 backgroundColor = LightOrange
             ),
             shape = RoundedCornerShape(37.dp),
             contentPadding = PaddingValues(vertical = 15.dp),
             elevation = ButtonDefaults.elevation(0.dp)
         ) {

             Text(text = "Start", style = TextStyle(fontSize = 18.sp,
                 fontWeight = FontWeight.W400, color = Color.Black))
         }
     }
    }
}

@Composable
fun titleText(){

    Text(
        text = "Programming Hero Quiz", style = TextStyle(
            fontSize = 20.sp, fontWeight = FontWeight.W400, textAlign = TextAlign.Center
        ), modifier = Modifier.padding(8.dp))
}

@Composable
fun hightScoreText(){
    Card(shape = RoundedCornerShape(37.dp),
        modifier = Modifier
            .width(300.dp)
            ) {
        Text(
            text = "High Score 5000", style = TextStyle(
                fontSize = 20.sp, fontWeight = FontWeight.W400,textAlign = TextAlign.Center
            ), modifier = Modifier.padding(16.dp))
    }
}