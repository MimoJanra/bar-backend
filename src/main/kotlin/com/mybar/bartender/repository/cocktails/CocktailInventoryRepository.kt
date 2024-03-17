package com.mybar.bartender.repository.cocktails

import com.mybar.bartender.model.cocktails.CocktailInventory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CocktailInventoryRepository : JpaRepository<CocktailInventory, Long> {
}