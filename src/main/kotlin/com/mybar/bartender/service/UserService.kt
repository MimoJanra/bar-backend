package com.mybar.bartender.service

import com.art.playersapi.exception.EntityNotFoundException
import com.mybar.bartender.dto.RegistrationUserDto
import com.mybar.bartender.dto.UserDto
import com.mybar.bartender.model.Role
import com.mybar.bartender.model.User
import com.mybar.bartender.repository.UserRepository
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserService(
    @Lazy
    private var passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val roleService: RoleService,
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = findByUsername(username)
            ?: throw UsernameNotFoundException(String.format("Пользователь '%s' не найден", username))
        return org.springframework.security.core.userdetails.User(
            user.name,
            user.password,
            user.roles!!.stream().map { x ->
                SimpleGrantedAuthority(
                    x!!.name
                )
            }.collect(Collectors.toList())
        )
    }

    fun findByUsername(username: String?): User? {
        return userRepository.findByName(username)
    }

    fun findByEmail(email: String?): User? {
        return userRepository.findByEmail(email!!)
    }

    fun getAllUsers(): List<UserDto> {
        return (userRepository.findAll() as List<User>).stream().map { x: User ->
            UserDto(
                x.id!!,
                x.name,
                x.email
            )
        }.collect(Collectors.toList())
    }

    fun createNewUser(registrationUserDto: RegistrationUserDto): User {
        val user = User(
            name = registrationUserDto.username,
            email = registrationUserDto.email,
            password = passwordEncoder.encode(registrationUserDto.password),
            roles = java.util.List.of(roleService.getUserRole())
        )
        return userRepository.save(user)
    }
}