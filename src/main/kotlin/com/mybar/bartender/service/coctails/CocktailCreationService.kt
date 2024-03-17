package com.mybar.bartender.service.coctails

import com.mybar.bartender.dto.CocktailDto
import com.mybar.bartender.model.cocktails.*
import com.mybar.bartender.repository.BarRepository
import com.mybar.bartender.repository.cocktails.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CocktailCreationService(
    private val cocktailRepository: CocktailRepository,
    private val tagRepository: TagRepository,
    private val ingredientRepository: IngredientRepository,
    private val inventoryItemRepository: InventoryItemRepository,
    private val cocktailTagRepository: TagRepository,
    private val cocktailIngredientRepository: IngredientRepository,
    private val cocktailInventoryRepository: InventoryItemRepository,
    private val recipeStepRepository: RecipeStepRepository,
    private val barRepository: BarRepository
) {
    @Transactional
    fun createCocktail(dto: CocktailDto): Cocktail {
        val bar = barRepository.findById(dto.barId).orElseThrow {
            RuntimeException("bar not found")
        }
        val cocktail = cocktailRepository.save(Cocktail(name = dto.name, rating = dto.rating, imagePath = dto.imagePath, bar = bar))

        dto.tags.forEach { tagName ->
            val tag = tagRepository.findByName(tagName) ?: tagRepository.save(Tag(name = tagName))
            cocktailTagRepository.save(CocktailTag(cocktail = cocktail, tag = tag))
        }

        dto.ingredients.forEach { ingredientDto ->
            val ingredient = ingredientRepository.findByName(ingredientDto.name) ?: ingredientRepository.save(Ingredient(name = ingredientDto.name))
            cocktailIngredientRepository.save(CocktailIngredient(cocktail = cocktail, ingredient = ingredient, amount = ingredientDto.amount, unit = ingredientDto.unit))
        }

        dto.inventoryItems.forEach { itemDto ->
            val item = inventoryItemRepository.findByName(itemDto.name) ?: inventoryItemRepository.save(InventoryItem(name = itemDto.name))
            cocktailInventoryRepository.save(CocktailInventory(cocktail = cocktail, inventoryItem = item, amount = itemDto.amount, unit = itemDto.unit))
        }

        dto.recipeSteps.forEach { stepDto ->
            val step = RecipeStep(
                cocktail = cocktail,
                stepNumber = stepDto.stepNumber,
                description = stepDto.description
            )
            recipeStepRepository.save(step)
        }

        return cocktail
    }
}