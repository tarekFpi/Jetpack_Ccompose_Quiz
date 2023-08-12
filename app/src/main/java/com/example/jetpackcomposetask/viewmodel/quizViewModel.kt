package com.example.jetpackcomposetask.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposetask.model.Question
import com.example.jetpackcomposetask.repository.quizRepository
import com.example.jetpackcomposetask.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class quizViewModel @Inject constructor(private val quizRepository: quizRepository
) : ViewModel(){


    private  var _response = mutableStateOf<NetworkResult<List<Question>>>(NetworkResult.Loading())
    val state_response: State<NetworkResult<List<Question>>> = _response



 /*   val quizListResponseLiveData : LiveData<NetworkResult<List<Article>>>
        get() = quizRepository.qustionResponseLiveData
*/

    init {

        viewModelScope.launch(Dispatchers.Main){

          quizRepository.qustionList().onStart {

              _response.value = NetworkResult.Loading()

          }.catch {

            _response.value =NetworkResult.Error(it.message)

            }.collect{

           _response.value =NetworkResult.Success(it.questions)
          }
        }
    }

}




