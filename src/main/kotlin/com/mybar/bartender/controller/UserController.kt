package com.mybar.bartender.controller

import com.mybar.bartender.model.User
import com.mybar.bartender.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {


}