package com.mybar.bartender.model.cocktails

import jakarta.persistence.*

@Entity
@Table(name = "InventoryItems")
data class InventoryItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(unique = true, nullable = false)
    val name: String
)