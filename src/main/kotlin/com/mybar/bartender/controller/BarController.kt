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
    fun createBar(@Valid @RequestBody barDto: BarDto): ResponseEntity<BarDto> {
        val createdBar = barService.createBar(barDto)
        return ResponseEntity.ok(createdBar.toDto())
    }

    @GetMapping
    fun getAllBars(): ResponseEntity<List<BarDto>> {
        val bars = barService.getAllBars().map { x -> x.toDto() }
        return ResponseEntity.ok(bars)
    }

    @DeleteMapping
    fun removeBar(@RequestParam barId: Long): ResponseEntity<Long> {
        return ResponseEntity.ok(barService.remove(barId))
    }
}