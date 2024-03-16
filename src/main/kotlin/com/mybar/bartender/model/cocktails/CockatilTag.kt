package com.mybar.bartender.model.cocktails

import jakarta.persistence.*

@Entity
@Table(name = "CocktailTags")
data class CocktailTag(
    @Id
    @ManyToOne
    @JoinColumn(name = "cocktail_id", referencedColumnName = "id", nullable = false)
    val cocktail: Cocktail,

    @Id
    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "id", nullable = false)
    val tag: Tag
)