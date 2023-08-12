@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)

package com.example.jetpackcomposetask.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcomposetask.R
import com.example.jetpackcomposetask.model.Question
import com.example.jetpackcomposetask.utils.NetworkResult
import com.example.jetpackcomposetask.view.ui.theme.*
import com.example.jetpackcomposetask.viewmodel.quizViewModel
import com.example.starbucks_ui.component.AppIconComponent
import com.example.starbucks_ui.screen.quiz_details
import okio.ByteString.Companion.encode


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navHostController: NavHostController
){
  // val  quizviewModel = viewModel(modelClass = quizViewModel::class.java)

    var chipStatus by remember {
        mutableStateOf("")
    }

    val viewModel: quizViewModel= hiltViewModel()

    var quiz_type by rememberSaveable{ mutableStateOf("") }

    var quiz_scroe by rememberSaveable{ mutableStateOf(0) }

    var questionImageUrl by rememberSaveable{ mutableStateOf("") }

    val context = LocalContext.current


    Scaffold(
        topBar = {
            TopAppBar(title = {

                Row(
                     modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = "Question: 2/3", fontSize = 18.sp, fontStyle = FontStyle.Italic, modifier = Modifier.padding(14.dp))
                    Text(text = "score: ${quiz_scroe}", fontSize = 18.sp, fontStyle = FontStyle.Italic, modifier = Modifier.padding(14.dp))
                }
            })
        }
    ) {



        Box(
            contentAlignment = Alignment.Center
        ){

            Column(modifier = Modifier
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .width(300.dp)
                        .padding(top = 8.dp)
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
                                painter = rememberAsyncImagePainter(questionImageUrl),
                                contentDescription = "Image Not Found",
                                modifier = Modifier.size(250.dp)
                            )

                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${quiz_type}", style = TextStyle(
                                fontSize = 20.sp, fontWeight = FontWeight.W400, color = Color.Red, textAlign = TextAlign.Center
                            ), modifier = Modifier.padding(8.dp))
                    }
                }

                when (val result = viewModel.state_response.value) {

                    is NetworkResult.Loading -> {

                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator()
                        }
                    }

                    is NetworkResult.Error -> {
                        Text(text = "${result}")
                    }

                    is NetworkResult.Success -> {

                        result.data?.let {
                            LazyColumn {
                                itemsIndexed(items = it.toList()) { index, item ->
                                    quizItemShow(question = item){

                                        quiz_type=item.question
                                        quiz_scroe=item.score

                                        if(!item.questionImageUrl.isNullOrEmpty()){
                                            questionImageUrl=item.questionImageUrl
                                        }else{
                                          Toast.makeText(context, "Image Not Found", Toast.LENGTH_SHORT).show()
                                        }

                                    }
                                }
                            }
                        }

                    }

                }
            }
        }


    }
}



@Composable
fun quizItemShow(question: Question, onClick: () -> Unit ){


    val context = LocalContext.current

  var  chiplist by remember {
        mutableStateOf(listOfNotNull(question.answers.A,question.answers.B,question.answers.C,question.answers.D))
    }


    var chipStatus by remember {
        mutableStateOf("")
    }

    var selected_Answer by remember { mutableStateOf (false) }

    var SelectColor by remember{ mutableStateOf (LightOrange) }


    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(18.dp),
      // backgroundColor = CategoryTwo,
       // backgroundColor = if(selected_Answer) Color.Green else Color.White,
        backgroundColor =if(selected_Answer) SelectColor else SelectColor,
        onClick = {
         onClick()
         // expanded = !expanded
          }
        ) {

             Column(
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(12.dp)
                     .padding(4.dp),

             ) {


                 Row (
                     modifier = Modifier
                         .fillMaxWidth(),
                     horizontalArrangement = Arrangement.SpaceBetween,

                     )   {

                     Text(text = question.question, fontSize = 16.sp,
                         fontWeight = FontWeight.W400, modifier = Modifier.width(260.dp))

                     AppIconComponent(icon = R.drawable.baseline_arrow_drop_down_24,)
                 }

                         Column {

                             chiplist.forEach {

                                SuggestionRow(label =it , select = it ==chipStatus){chip->
                                     chipStatus = chip


                                    if (question.correctAnswer.equals("A") && question.answers.A.equals(it)) {
                                        selected_Answer = !selected_Answer
                                        SelectColor = Color.Green

                                    } else if (question.correctAnswer.equals("B") && question.answers.B.equals(it)) {
                                        selected_Answer = !selected_Answer
                                        SelectColor = Color.Green

                                    } else if (question.correctAnswer.equals("C") && question.answers.C.equals(it)) {
                                        selected_Answer = !selected_Answer
                                        SelectColor = Color.Green

                                    }else if (question.correctAnswer.equals("D") && question.answers.D.equals(it)) {
                                        selected_Answer = !selected_Answer
                                        SelectColor = Color.Green

                                    } else {

                                        Toast.makeText(context, "Not correctAnswer", Toast.LENGTH_SHORT).show()
                                        selected_Answer = selected_Answer
                                        SelectColor = Color.Red
                                    }

                                }
                             }

                         }
             }
    }
}


@Composable
fun SuggestionRow(
    label:String,
    select:Boolean,
    onChipChange:(String)->Unit,
){

    SuggestionChip(onClick = {
        if (!select)
          onChipChange(label)
        else
            onChipChange(label)

    }, label = {
        Text(text = label, style = TextStyle(fontSize = 14.sp, color = if(select) Color.White else Color.Black))
    },
        shape = RoundedCornerShape(8.dp),
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = if(select) Purple500 else Color.Transparent
        ),
        border = SuggestionChipDefaults.suggestionChipBorder(
            borderWidth = 2.dp,
            borderColor = if(select) Color.Transparent else Purple500
        ),
        modifier = Modifier.padding(4.dp)

    )
}




