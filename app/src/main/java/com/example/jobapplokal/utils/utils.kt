package com.example.jobapplokal.utils

const val BASE_URL="https://testapi.getlokalapp.com"


sealed class ResultState<out T> {
    class Success<T>(var data: T) : ResultState<T>()
    class Error(var exception: Throwable) : ResultState<Nothing>()
    data object Loading : ResultState<Nothing>()
}

const val tableName="Job_Details"