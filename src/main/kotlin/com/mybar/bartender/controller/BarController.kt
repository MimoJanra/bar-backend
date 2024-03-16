package com.mybar.bartender.controller

import com.mybar.bartender.dto.BarDto
import com.mybar.bartender.service.BarService
import com.mybar.bartender.model.Bar
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/bars")
class BarController(private val barService: BarService) {

    @PostMapping
    fun createBar(@Valid @RequestBody barDto: BarDto): ResponseEntity<Bar> {
        val createdBar = barService.createBar(barDto)
        return ResponseEntity.ok(createdBar)
    }

    @GetMapping
    fun getAllBars(): ResponseEntity<List<Bar>> {
        val bars = barService.getAllBars()
        return ResponseEntity.ok(bars)
    }
}