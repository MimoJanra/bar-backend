package com.mybar.bartender.repository.cocktails

import org.springframework.data.jpa.repository.JpaRepository
import com.mybar.bartender.model.cocktails.RecipeStep

interface RecipeStepRepository : JpaRepository<RecipeStep, Long> {
}