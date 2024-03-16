package com.mybar.bartender.repository.cocktails

import com.mybar.bartender.model.cocktails.Cocktail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CocktailRepository : JpaRepository<Cocktail, Long> {
    fun findByNameContainingIgnoreCase(name: String): List<Cocktail>
}
