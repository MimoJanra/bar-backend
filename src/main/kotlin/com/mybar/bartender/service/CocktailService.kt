package com.mybar.bartender.service

import com.mybar.bartender.model.cocktails.Cocktail
import com.mybar.bartender.repository.CocktailRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CocktailService(private val cocktailRepository: CocktailRepository) {

    fun findAllCocktails(): List<Cocktail> = cocktailRepository.findAll()

    fun getCocktailById(id: Long): Cocktail? = cocktailRepository.findById(id).orElse(null)

    fun searchCocktailsByName(name: String): List<Cocktail> =
        cocktailRepository.findByNameContainingIgnoreCase(name)

    @Transactional
    fun createCocktail(cocktail: Cocktail): Cocktail = cocktailRepository.save(cocktail)

    @Transactional
    fun updateCocktail(id: Long, updatedCocktail: Cocktail): Cocktail? {
        val cocktail = cocktailRepository.findById(id).orElse(null) ?: return null
        cocktail.name = updatedCocktail.name
        cocktail.rating = updatedCocktail.rating
        cocktail.imagePath = updatedCocktail.imagePath
        return cocktailRepository.save(cocktail)
    }

    fun deleteCocktail(id: Long) = cocktailRepository.deleteById(id)
}