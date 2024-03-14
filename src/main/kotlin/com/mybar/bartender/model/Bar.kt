package com.mybar.bartender.model

import jakarta.persistence.*

@Entity
@Table(name = "bars")
data class Bar(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val location: String
)