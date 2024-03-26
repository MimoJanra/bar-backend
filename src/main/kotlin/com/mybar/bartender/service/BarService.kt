package com.mybar.bartender.service

import com.art.playersapi.exception.EntityNotFoundException
import com.mybar.bartender.dto.BarDto
import com.mybar.bartender.model.Bar
import com.mybar.bartender.model.User
import com.mybar.bartender.repository.BarRepository
import com.mybar.bartender.repository.UserRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class BarService(
    private var authService: AuthService,
    private val barRepository: BarRepository,
    private val userRepository: UserRepository
) {

    fun createBar(dto: BarDto): Bar {
        var id = authService.getUserId();
        var user: User = userRepository.findById(id).getOrNull()
            ?: throw EntityNotFoundException(id)
        val bar = dto.toEntity(user)
        return barRepository.save(bar)
    }

    fun getAllBars(): List<Bar> = barRepository.findAll()

    fun remove(barId: Long): Long? {
        var bar = barRepository.findById(barId).getOrNull()
            ?: throw EntityNotFoundException(barId);
        barRepository.delete(bar);
        return barId;
    }
}