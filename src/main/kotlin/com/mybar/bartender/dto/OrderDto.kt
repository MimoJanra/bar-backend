package com.mybar.bartender.dto

import java.time.LocalDateTime

class OrderDto(
    val userId: Long,
    val barId: Long,
    val orderItems: List<OrderItemDto>,
    val note: String,
    val totalCost: Double,
    val status: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)