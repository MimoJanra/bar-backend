package com.mybar.bartender.repository

import com.mybar.bartender.model.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name : String) : Role?
}