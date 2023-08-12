package com.example.jetpackcomposetask.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposetask.model.Question
import com.example.jetpackcomposetask.screen.HomeScreen
import com.example.jetpackcomposetask.screen.StartScreen
import com.example.jetpackcomposetask.utils.NetworkResult
import com.example.jetpackcomposetask.view.ui.theme.JetpackComposeTaskTheme
import com.example.jetpackcomposetask.viewmodel.quizViewModel
import com.example.starbucks_ui.screen.StarBucksNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

 //   private val quiz_ViewModel:quizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTaskTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        StarBucksNavigation()
                    }
                }
            }
        }
    }
}



