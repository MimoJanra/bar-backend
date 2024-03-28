package com.mybar.bartender.model

import jakarta.persistence.*
import java.time.LocalDateTime

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
) {
    fun addOrderItem(orderItem: OrderItem) {
        orderItems.add(orderItem)
        orderItem.order = this
    }

    fun removeOrderItem(orderItem: OrderItem) {
        orderItems.remove(orderItem)
        orderItem.order = null
    }
}