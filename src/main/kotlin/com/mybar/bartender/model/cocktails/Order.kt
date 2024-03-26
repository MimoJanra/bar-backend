package com.mybar.bartender.model.cocktails

import com.mybar.bartender.model.Bar
import com.mybar.bartender.model.OrderItem
import com.mybar.bartender.model.OrderStatus
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false)
    val date: Date,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bar_id", nullable = false)
    val bar: Bar,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_status_id", nullable = false)
    val orderStatus: OrderStatus,
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val orderItems: Set<OrderItem> = HashSet(),
    @Column(length = 255)
    val note: String,
)