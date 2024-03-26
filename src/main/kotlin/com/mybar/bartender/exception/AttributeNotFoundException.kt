package com.art.playersapi.exception

import org.springframework.http.HttpStatus

class AttributeNotFoundException(name: String) : BaseException(
    HttpStatus.BAD_REQUEST,
    ApiError(
        "entity.attibute.not.found",
        "Entity attibute named=$name is null"
    )
)