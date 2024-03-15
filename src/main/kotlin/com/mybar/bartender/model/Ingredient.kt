package com.mybar.bartender.model

import jakarta.persistence.*

@Entity
@Table(name = "ingredients")
class Ingredient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 255)
    val name: String
)