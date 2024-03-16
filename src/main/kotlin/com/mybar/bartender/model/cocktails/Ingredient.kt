package com.mybar.bartender.model.cocktails

import jakarta.persistence.*

@Entity
@Table(name = "Ingredients")
data class Ingredient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val name: String
)