package com.mybar.bartender.service.coctails

import com.mybar.bartender.model.cocktails.InventoryItem
import com.mybar.bartender.repository.cocktails.InventoryItemRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class InventoryItemService(private val inventoryItemRepository: InventoryItemRepository) {

    fun findAllItems(): List<InventoryItem> = inventoryItemRepository.findAll()

    fun findItemById(id: Long): InventoryItem? = inventoryItemRepository.findById(id).orElse(null)

    fun findItemByName(name: String): InventoryItem? = inventoryItemRepository.findByName(name)

    @Transactional
    fun createItem(item: InventoryItem): InventoryItem = inventoryItemRepository.save(item)

    @Transactional
    fun updateItem(id: Long, updatedItem: InventoryItem): InventoryItem? {
        return if (inventoryItemRepository.existsById(id)) {
            updatedItem.id = id
            inventoryItemRepository.save(updatedItem)
        } else null
    }

    @Transactional
    fun deleteItem(id: Long) {
        inventoryItemRepository.deleteById(id)
    }
}