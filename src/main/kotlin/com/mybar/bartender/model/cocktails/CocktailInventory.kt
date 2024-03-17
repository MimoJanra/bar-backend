package com.mybar.bartender.model.cocktails

import jakarta.persistence.*

@Entity
@Table(name = "CocktailInventory")
data class CocktailInventory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "cocktail_id", nullable = false)
    val cocktail: Cocktail,

    @ManyToOne
    @JoinColumn(name = "inventory_item_id", nullable = false)
    val inventoryItem: InventoryItem,

    val amount: String,

    val unit: String
)