package com.example.jetpackcomposetask.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackcomposetask.model.Question
import com.example.jetpackcomposetask.model.quiz_response
import com.example.jetpackcomposetask.retrofit.ApiService
import com.example.jetpackcomposetask.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class  quizRepository@Inject constructor(private val apiService: ApiService) {

      fun qustionList()  = flow {

        emit(apiService.getPost())

    }.flowOn(Dispatchers.IO)
}
