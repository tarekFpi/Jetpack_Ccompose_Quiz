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

    suspend fun qustionList()  = flow {

        emit(apiService.getPost())

    }.flowOn(Dispatchers.IO)



 /*   private var _qustionListResponseLiveData = MutableLiveData<NetworkResult<List<Article>>>()
    val  qustionResponseLiveData : LiveData<NetworkResult<List<Article>>>
    get() =_qustionListResponseLiveData

    suspend  fun qustionList(){

        _qustionListResponseLiveData.postValue(NetworkResult.Loading())

        try {
            val response = apiService.getPost()

            if (response.isSuccessful && response.body() != null) {

          _qustionListResponseLiveData.postValue(NetworkResult.Success(response.body()!!.articles))

            } else if (response.errorBody() != null) {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _qustionListResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            } else {
                _qustionListResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }

        } catch(e: Exception) {

            _qustionListResponseLiveData.postValue(NetworkResult.Error(e.message!!))

            Log.d("Exception","${e.message}")
        }
    }
*/




}
