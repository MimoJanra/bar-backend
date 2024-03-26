package com.mybar.bartender.repository.cocktails

import com.mybar.bartender.model.cocktails.RecipeStep
import org.springframework.data.jpa.repository.JpaRepository

interface RecipeStepRepository : JpaRepository<RecipeStep, Long> {
}