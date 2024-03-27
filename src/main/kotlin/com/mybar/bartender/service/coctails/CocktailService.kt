package com.mybar.bartender.service.coctails

import com.mybar.bartender.dto.CocktailDto
import com.mybar.bartender.model.cocktails.Cocktail
import com.mybar.bartender.repository.BarRepository
import com.mybar.bartender.repository.cocktails.CocktailRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CocktailService(
    private val cocktailRepository: CocktailRepository,
    private val barRepository: BarRepository
) {

    fun findAllCocktails(): List<Cocktail> = cocktailRepository.findAll()

    fun findCocktailByName(name: String): Cocktail? =
        cocktailRepository.findByName(name)

    fun getCocktailById(id: Long): Cocktail? = cocktailRepository.findById(id).orElse(null)

    @Transactional
    fun createCocktail(cocktail: CocktailDto, barId: Long): Cocktail {
        val bar = barRepository.findById(barId).orElseThrow {
            RuntimeException("bar not found")
        }
        return cocktailRepository.save(cocktail.toEntity(bar))
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
        cocktailRepository.deleteById(cocktailId)
    }
}
