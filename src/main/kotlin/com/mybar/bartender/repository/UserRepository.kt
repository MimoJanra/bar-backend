package com.mybar.bartender.repository

import com.mybar.bartender.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String?): User?;
    fun findByEmail(email: String): User?;
}