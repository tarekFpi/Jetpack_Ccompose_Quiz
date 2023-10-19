package com.example.jetpackcomposetask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Question(
    val answers: Answers,
    val correctAnswer: String,
    val question: String,
    val questionImageUrl: String,
    val score: Int
)