package com.art.playersapi.exception

data class ApiError(
    val errorCode: String,
    var description: String
)