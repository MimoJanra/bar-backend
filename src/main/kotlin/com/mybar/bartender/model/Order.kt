package com.mybar.bartender.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    val barId: Long,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val orderItems: List<OrderItem>,

    val note: String,

    val totalCost: Double,

    @Enumerated(EnumType.STRING)
    val status: OrderStatus,

    val createdAt: LocalDateTime = LocalDateTime.now()
)
