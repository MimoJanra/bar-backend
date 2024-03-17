package com.mybar.bartender.model.cocktails

import jakarta.persistence.*

@Entity
@Table(name = "CocktailIngredients")
data class CocktailIngredient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "cocktail_id", nullable = false)
    val cocktail: Cocktail,

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    val ingredient: Ingredient,

    val amount: String,

    val unit: String
)