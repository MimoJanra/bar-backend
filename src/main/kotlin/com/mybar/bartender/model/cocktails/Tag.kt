package com.mybar.bartender.model.cocktails

import jakarta.persistence.*

@Entity
@Table(name = "Tags")
data class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true, length = 255)
    val name: String,

    @OneToMany(mappedBy = "tag")
    val cocktailTags: Set<CocktailTag> = HashSet()
)