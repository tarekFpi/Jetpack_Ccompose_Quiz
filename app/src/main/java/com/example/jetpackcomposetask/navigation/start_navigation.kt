package com.example.starbucks_ui.screen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposetask.model.Question
import com.example.jetpackcomposetask.screen.DetailScreen
import com.example.jetpackcomposetask.screen.HomeScreen
import com.example.jetpackcomposetask.screen.StartScreen

@Composable
fun StarBucksNavigation() {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = start){
        composable(start){
            StartScreen(navHostController = navHostController)

        }
        composable(home){
           HomeScreen(navHostController = navHostController)
        }
        composable(quiz_details){

           DetailScreen(navHostController = navHostController)
        }
    }
}

const val start = "start_screen"
const val home = "home_screen"
const val quiz_details ="product_detail_screen"