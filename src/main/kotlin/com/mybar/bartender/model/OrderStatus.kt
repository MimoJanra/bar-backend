package com.mybar.bartender.model

import jakarta.persistence.*

@Entity
@Table(name = "orderStatus")
class OrderStatus(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val code: String,

    @Column(nullable = false)
    val name: String,
)