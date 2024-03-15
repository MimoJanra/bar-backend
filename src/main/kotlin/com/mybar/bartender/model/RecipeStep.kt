package com.mybar.bartender.model

import jakarta.persistence.*

@Entity
@Table(name = "recipeStep")
class RecipeStep(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cocktail_id", nullable = false)
    var cocktail: Cocktail,

    @Column(nullable = false)
    val stepNumber: Int,

    @Column(nullable = false)
    val description: String,
)