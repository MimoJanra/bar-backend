package com.mybar.bartender.service

import com.mybar.bartender.model.User
import com.mybar.bartender.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    fun registerUser(email: String, password: String): User {
        val encryptedPassword = passwordEncoder.encode(password)
        val user = User(email = email, password = encryptedPassword)
        return userRepository.save(user)
    }
}