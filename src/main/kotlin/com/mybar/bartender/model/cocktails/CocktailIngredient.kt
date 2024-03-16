package com.mybar.bartender.model.cocktails

import jakarta.persistence.*

@Entity
@Table(name = "CocktailIngredients")
data class CocktailIngredient(
    @Id
    @ManyToOne
    @JoinColumn(name = "cocktail_id", referencedColumnName = "id", nullable = false)
    val cocktail: Cocktail,

    @Id
    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = false)
    val ingredient: Ingredient,

    @Column(nullable = false)
    val amount: String,

    @Column(nullable = false)
    val unit: String,

    @Column(nullable = false)
    val type: String
)