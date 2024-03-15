package com.mybar.bartender.model

import jakarta.persistence.*

@Entity
@Table(name = "cocktailIngredients")
class CocktailIngredient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cocktail_id", nullable = false)
    var cocktail: Cocktail,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingredient_id", nullable = false)
    var ingredient: Ingredient,

    @Column(nullable = false, length = 255)
    val amount: String,

    @Column(nullable = false, length = 255)
    val unit: String,

    @Column(nullable = false, length = 255)
    val type: String,
)