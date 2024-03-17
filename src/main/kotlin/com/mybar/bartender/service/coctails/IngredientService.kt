package com.mybar.bartender.service.coctails

import com.mybar.bartender.model.cocktails.Ingredient
import com.mybar.bartender.repository.cocktails.IngredientRepository
import org.springframework.stereotype.Service

@Service
class IngredientService(private val ingredientRepository: IngredientRepository) {

    fun findAllIngredients(): List<Ingredient> = ingredientRepository.findAll()

    fun findIngredientById(id: Long): Ingredient? = ingredientRepository.findById(id).orElse(null)

    fun createIngredient(ingredient: Ingredient): Ingredient = ingredientRepository.save(ingredient)

    fun updateIngredient(id: Long, updatedIngredient: Ingredient): Ingredient? {
        return if (ingredientRepository.existsById(id)) {
            ingredientRepository.save(updatedIngredient)
        } else null
    }

    fun deleteIngredient(id: Long) {
        ingredientRepository.deleteById(id)
    }
}