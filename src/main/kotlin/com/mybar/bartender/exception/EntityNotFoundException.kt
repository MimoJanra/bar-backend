package com.art.playersapi.exception

import org.springframework.http.HttpStatus

class EntityNotFoundException(id: Long) : BaseException(
    httpStatus = HttpStatus.NOT_FOUND,
    apiError = ApiError(
        "entity.not.found",
        "Entity not found with id=$id"
    )
)