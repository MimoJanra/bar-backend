package com.mybar.bartender.service

import com.mybar.bartender.model.Bar
import com.mybar.bartender.repository.BarRepository
import org.springframework.stereotype.Service

@Service
class BarService(private val barRepository: BarRepository) {

    fun createBar(name: String, location: String): Bar {
        val bar = Bar(name = name, location = location)
        return barRepository.save(bar)
    }

    fun getAllBars(): List<Bar> = barRepository.findAll()
}