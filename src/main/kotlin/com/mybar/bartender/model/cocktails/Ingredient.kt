package com.mybar.bartender.model.cocktails

import jakarta.persistence.*

@Entity
@Table(name = "Ingredients")
data class Ingredient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true, length = 255)
    val name: String,

    @OneToMany(mappedBy = "ingredient")
    val cocktailIngredients: Set<CocktailIngredient> = HashSet()
)