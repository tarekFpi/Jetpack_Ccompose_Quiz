package com.example.jetpackcomposetask.retrofit
import com.example.jetpackcomposetask.model.Question
import com.example.jetpackcomposetask.model.quiz_response
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("quiz.json")
     suspend fun getPost(): quiz_response
}