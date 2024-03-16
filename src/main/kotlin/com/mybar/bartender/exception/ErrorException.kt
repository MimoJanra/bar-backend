package com.art.playersapi.exception

import org.springframework.http.HttpStatus

class ErrorException(desctriprion: String) : BaseException(
    HttpStatus.BAD_REQUEST,
    ApiError("error", desctriprion)
)