package com.mybar.bartender.controller

import com.mybar.bartender.dto.JwtRequest
import com.mybar.bartender.dto.RegistrationUserDto
import com.mybar.bartender.dto.UserDto
import com.mybar.bartender.service.AuthService
import com.mybar.bartender.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val authService: AuthService,
    private val userService: UserService
) {
    @PostMapping("/auth")
    fun createAuthToken(@RequestBody authRequest: JwtRequest?): ResponseEntity<*> {
        return authService.createAuthToken(authRequest)
    }

    @PostMapping("/registration")
    fun createNewUser(@RequestBody registrationUserDto: RegistrationUserDto?): ResponseEntity<*> {
        return authService.createNewUser(registrationUserDto)
    }

    @GetMapping("/users")
    fun getUser(): List<UserDto> {
        return userService.getAllUsers();
    }

    @GetMapping("/roles")
    fun getRole(): String {
        return "roles"
    }

    @GetMapping("/info")
    fun getInfo(): String {
        return "info"
    }
}