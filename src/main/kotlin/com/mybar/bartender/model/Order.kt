package com.mybar.bartender.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.HashSet

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false)
    val userId: Long,
    @Column(nullable = false)
    val barId: Long,
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var orderItems: MutableSet<OrderItem> = HashSet(),
    @Column(length = 255)
    val note: String,
    @Column(nullable = false)
    val totalCost: Double,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: OrderStatus,
    @Column(nullable = false)
    val createdAt: LocalDateTime
)
