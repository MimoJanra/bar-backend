package com.mybar.bartender.service

import com.mybar.bartender.model.cocktails.Cocktail
import com.mybar.bartender.repository.CocktailRepository
import com.mybar.bartender.repository.UserRepository
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
    fun createCocktail(cocktail: Cocktail, userId: Long): Cocktail {
        val user = userRepository.findById(userId).orElseThrow {
            RuntimeException("User not found")
        }
        cocktail.user = user
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

    fun deleteCocktail(cocktailId: Long, userId: Long) {
        val cocktail = cocktailRepository.findById(cocktailId).orElseThrow {
            RuntimeException("Cocktail not found")
        }
        if (cocktail.user.id == userId) {
            cocktailRepository.deleteById(cocktailId)
        } else {
            throw SecurityException("You are not authorized to delete this cocktail")
        }
    }
}
