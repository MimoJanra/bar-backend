package com.mybar.bartender.controller.cocktails

import com.mybar.bartender.model.cocktails.InventoryItem
import com.mybar.bartender.service.coctails.InventoryItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/inventoryItems")
class InventoryItemController(private val inventoryItemService: InventoryItemService) {

    @GetMapping
    fun getAllItems(): ResponseEntity<List<InventoryItem>> = ResponseEntity.ok(inventoryItemService.findAllItems())

    @GetMapping("/{id}")
    fun getItemById(@PathVariable id: Long): ResponseEntity<InventoryItem> =
        inventoryItemService.findItemById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun createItem(@RequestBody item: InventoryItem): ResponseEntity<InventoryItem> =
        ResponseEntity.ok(inventoryItemService.createItem(item))

    @PutMapping("/{id}")
    fun updateItem(@PathVariable id: Long, @RequestBody item: InventoryItem): ResponseEntity<InventoryItem> =
        inventoryItemService.updateItem(id, item)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable id: Long): ResponseEntity<Void> {
        inventoryItemService.deleteItem(id)
        return ResponseEntity.ok().build()
    }
}