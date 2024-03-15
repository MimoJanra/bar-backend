package com.mybar.bartender.model

import jakarta.persistence.*

@Entity
@Table(name = "coctails")
class Cocktail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 255)
    val name: String,

    @Column(nullable = false, length = 255)
    var ratio: Int = 0,

    @Column(nullable = false, length = 255)
    val imagePath: String,

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "cocktail")
    private var cocktailIngredient: List<CocktailIngredient>? = mutableListOf(),

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "cocktail")
    private var recipeStep: List<RecipeStep>? = mutableListOf()
)