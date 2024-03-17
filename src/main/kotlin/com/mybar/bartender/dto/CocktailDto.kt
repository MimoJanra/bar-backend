package com.mybar.bartender.dto

import com.mybar.bartender.model.Bar
import com.mybar.bartender.model.cocktails.Cocktail
import java.math.BigDecimal

data class CocktailDto(
    val name: String,
    val rating: BigDecimal,
    val imagePath: String?,
    val tags: List<String>,
    val ingredients: List<IngredientDto>,
    val inventoryItems: List<InventoryItemDto>,
    val recipeSteps: List<RecipeStepDto>,
    val barId : Long) {
    fun toEntity(bar: Bar): Cocktail {
        return Cocktail(
            name = this.name, rating = this.rating, imagePath = this.imagePath, bar = bar
        )
    }
}