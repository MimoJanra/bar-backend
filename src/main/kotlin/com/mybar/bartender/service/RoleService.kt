package com.mybar.bartender.service

import com.mybar.bartender.model.Role
import com.mybar.bartender.repository.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleService(
    private var roleRepository: RoleRepository
) {
    fun getUserRole(): Role {
        return roleRepository.findByName("ROLE_USER")!!
    }
}