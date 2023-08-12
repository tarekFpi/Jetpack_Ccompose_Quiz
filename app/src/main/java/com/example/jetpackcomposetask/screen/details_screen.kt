@file:OptIn(ExperimentalMaterialApi::class)

package com.example.jetpackcomposetask.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpackcomposetask.R
import com.example.jetpackcomposetask.model.Question
import com.example.jetpackcomposetask.view.ui.theme.CategoryTwo
import com.example.jetpackcomposetask.view.ui.theme.DarkGray
import com.example.jetpackcomposetask.view.ui.theme.Gray500
import com.example.jetpackcomposetask.view.ui.theme.LightRed_1
import com.example.starbucks_ui.component.AppIconComponent
import com.example.starbucks_ui.screen.quiz_details

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navHostController: NavHostController){


   val quizlist=
        navHostController.previousBackStackEntry?.savedStateHandle?.get<Question>("quizlist")

    Log.d("detailArgument:","${quizlist?.question}")


Scaffold(
    topBar = {
        TopAppBar(title = {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = "Question: 2/3", fontSize = 18.sp, fontStyle = FontStyle.Italic, modifier = Modifier.padding(14.dp))

                Text(text = "score: 3000", fontSize = 18.sp, fontStyle = FontStyle.Italic, modifier = Modifier.padding(14.dp))
            }

        })
    }
) {
    Box (
        modifier = Modifier
            .background(Gray500),
        contentAlignment = Alignment.Center
    ){

        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .width(300.dp).padding(top = 8.dp)
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

            LazyColumn {
                items(5) { item ->
                    AnswersItemShow(){

                    }
                }
            }
        }
    }
}


}

@Composable
fun AnswersItemShow(onClick: () -> Unit ){

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(18.dp),
        backgroundColor = CategoryTwo,
        onClick = {
            onClick()
        }
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

            ){

            Text(text = "Java", fontSize = 18.sp,
                fontWeight = FontWeight.W400, modifier = Modifier.width(260.dp))

            AppIconComponent(icon = R.drawable.baseline_arrow_drop_down_24)

        }

    }
}

