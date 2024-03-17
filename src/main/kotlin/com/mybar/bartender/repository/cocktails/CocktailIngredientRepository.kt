package com.mybar.bartender.repository.cocktails

import com.mybar.bartender.model.cocktails.CocktailIngredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CocktailIngredientRepository : JpaRepository<CocktailIngredient, Long> {
}