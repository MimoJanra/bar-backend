package com.mybar.bartender.service.cocktails

import com.mybar.bartender.dto.CocktailDto
import com.mybar.bartender.model.cocktails.*
import com.mybar.bartender.repository.cocktails.*
import org.springframework.stereotype.Service
import jakarta.transaction.Transactional

@Service
class CocktailCreationService(
    private val cocktailRepository: CocktailRepository,
    private val tagRepository: TagRepository,
    private val ingredientRepository: IngredientRepository,
    private val inventoryItemRepository: InventoryItemRepository,
    private val cocktailTagRepository: CocktailTagRepository,
    private val cocktailIngredientRepository: CocktailIngredientRepository,
    private val cocktailInventoryRepository: CocktailInventoryRepository,
    private val recipeStepRepository: RecipeStepRepository
) {
    @Transactional
    fun createCocktail(dto: CocktailDto): Cocktail {
        val cocktail = Cocktail(name = dto.name, rating = dto.rating, imagePath = dto.imagePath).let {
            cocktailRepository.save(it)
        }

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
            recipeStepRepository.save(RecipeStep(cocktail = cocktail, stepNumber = stepDto.stepNumber, description = stepDto.description))
        }

        return cocktail
    }
}
