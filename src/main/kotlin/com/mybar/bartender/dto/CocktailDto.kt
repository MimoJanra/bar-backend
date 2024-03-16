package com.mybar.bartender.dto

import java.math.BigDecimal

data class CocktailDto(
    val name: String,
    val rating: BigDecimal,
    val imagePath: String?,
    val tags: List<String>,
    val ingredients: List<IngredientDto>,
    val inventoryItems: List<InventoryItemDto>,
    val recipeSteps: List<RecipeStepDto>
)