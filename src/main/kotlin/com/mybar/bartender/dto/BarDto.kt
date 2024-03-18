package com.mybar.bartender.dto

import com.mybar.bartender.model.Bar
import com.mybar.bartender.model.User

class BarDto(
    val id: Long = 0,
    val name: String,
    val location: String
) {
    fun toEntity(user: User): Bar {
        return Bar(name = this.name, location = this.location, user = user);
    }
}