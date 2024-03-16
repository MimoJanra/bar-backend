package com.mybar.bartender.repository.cocktails

import com.mybar.bartender.model.cocktails.InventoryItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InventoryItemRepository : JpaRepository<InventoryItem, Long> {
    fun findByName(name: String): InventoryItem?
}