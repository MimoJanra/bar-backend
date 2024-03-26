package com.mybar.bartender.controller.cocktails

import com.mybar.bartender.model.cocktails.Ingredient
import com.mybar.bartender.service.coctails.IngredientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/ingredients")
class IngredientController(private val ingredientService: IngredientService) {

    @GetMapping
    fun getAllIngredients(): ResponseEntity<List<Ingredient>> =
        ResponseEntity.ok(ingredientService.findAllIngredients())

    @PostMapping
    fun createIngredient(@RequestBody ingredient: Ingredient): ResponseEntity<Ingredient> =
        ResponseEntity.ok(ingredientService.createIngredient(ingredient))

    @PutMapping("/{id}")
    fun updateIngredient(@PathVariable id: Long, @RequestBody ingredient: Ingredient): ResponseEntity<Ingredient> =
        ingredientService.updateIngredient(id, ingredient)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound()
            .build()

    @DeleteMapping("/{id}")
    fun deleteIngredient(@PathVariable id: Long): ResponseEntity<Void> {
        ingredientService.deleteIngredient(id)
        return ResponseEntity.ok().build()
    }
}