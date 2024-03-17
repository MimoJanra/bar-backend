package com.mybar.bartender.service.coctails

import com.mybar.bartender.model.cocktails.Cocktail
import com.mybar.bartender.repository.UserRepository
import com.mybar.bartender.repository.cocktails.CocktailRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CocktailService(
    private val cocktailRepository: CocktailRepository,
    private val userRepository: UserRepository
) {

    fun findAllCocktails(): List<Cocktail> = cocktailRepository.findAll()

    fun getCocktailById(id: Long): Cocktail? = cocktailRepository.findById(id).orElse(null)

    fun searchCocktailsByName(name: String): List<Cocktail> =
        cocktailRepository.findByNameContainingIgnoreCase(name)

    @Transactional
    fun createCocktail(cocktail: Cocktail): Cocktail {
        return cocktailRepository.save(cocktail)
    }

    @Transactional
    fun updateCocktail(id: Long, updatedCocktail: Cocktail): Cocktail? {
        val cocktail = cocktailRepository.findById(id).orElse(null) ?: return null
        cocktail.name = updatedCocktail.name
        cocktail.rating = updatedCocktail.rating
        cocktail.imagePath = updatedCocktail.imagePath
        return cocktailRepository.save(cocktail)
    }

    fun deleteCocktail(cocktailId: Long) {
        cocktailRepository.deleteById(cocktailId)
    }
}
