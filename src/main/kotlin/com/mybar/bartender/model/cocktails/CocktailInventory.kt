package com.mybar.bartender.model.cocktails

import com.mybar.bartender.dto.InventoryItemDto
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
) {
    fun toDto(): InventoryItemDto {
        return InventoryItemDto(this.inventoryItem.name, this.amount, this.unit)
    }
}