package com.mybar.bartender.model.cocktails

import jakarta.persistence.*

@Entity
@Table(name = "Tags")
data class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val name: String
)