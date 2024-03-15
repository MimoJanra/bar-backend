package com.mybar.bartender.model

import jakarta.persistence.*

@Entity
@Table(name = "tags")
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 255)
    val name: String,
)