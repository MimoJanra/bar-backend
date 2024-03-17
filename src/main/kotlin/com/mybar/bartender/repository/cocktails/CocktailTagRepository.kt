package com.mybar.bartender.repository.cocktails

import com.mybar.bartender.model.cocktails.CocktailTag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CocktailTagRepository : JpaRepository<CocktailTag, Long> {
}