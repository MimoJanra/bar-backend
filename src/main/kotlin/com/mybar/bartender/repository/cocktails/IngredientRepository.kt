package com.mybar.bartender.repository.cocktails

import com.mybar.bartender.model.cocktails.CocktailIngredient
import com.mybar.bartender.model.cocktails.Ingredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientRepository : JpaRepository<Ingredient, Long> {
    fun findByName(name: String): Ingredient?
    abstract fun save(cocktailIngredient: CocktailIngredient)
}