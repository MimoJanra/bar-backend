package com.mybar.bartender.model.cocktails

import jakarta.persistence.*

@Entity
@Table(name = "CocktailTags")
data class CocktailTag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "cocktail_id", nullable = false)
    val cocktail: Cocktail,

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    val tag: Tag
)