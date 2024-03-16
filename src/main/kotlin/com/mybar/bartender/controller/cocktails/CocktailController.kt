package com.mybar.bartender.controller.cocktails

import com.mybar.bartender.dto.CocktailDto
import com.mybar.bartender.model.cocktails.Cocktail
import com.mybar.bartender.service.coctails.CocktailCreationService
import com.mybar.bartender.service.coctails.CocktailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cocktails")
class CocktailController(
    private val cocktailService: CocktailService,
    @Autowired private val cocktailCreationService: CocktailCreationService
) {

    @GetMapping
    fun getAllCocktails(): ResponseEntity<List<Cocktail>> {
        val cocktails = cocktailService.findAllCocktails()
        return ResponseEntity.ok(cocktails)
    }

    @GetMapping("/{id}")
    fun getCocktailById(@PathVariable id: Long): ResponseEntity<Cocktail> {
        val cocktail = cocktailService.getCocktailById(id)
        return if (cocktail != null) ResponseEntity.ok(cocktail) else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createCocktail(@RequestBody cocktailDto: CocktailDto): ResponseEntity<Cocktail> {
        val savedCocktail = cocktailCreationService.createCocktail(cocktailDto)
        return ResponseEntity.ok(savedCocktail)
    }

    @PutMapping("/{id}")
    fun updateCocktail(@PathVariable id: Long, @RequestBody cocktail: Cocktail): ResponseEntity<Cocktail> {
        val updatedCocktail = cocktailService.updateCocktail(id, cocktail)
        return if (updatedCocktail != null) ResponseEntity.ok(updatedCocktail) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteCocktail(@PathVariable id: Long): ResponseEntity<Void> {
        val userId = getCurrentUserId()
        cocktailService.deleteCocktail(id, userId)
        return ResponseEntity.ok().build()
    }

    private fun getCurrentUserId(): Long {
        return 1
    }

    @GetMapping("/search")
    fun searchCocktailsByName(@RequestParam("name") name: String): ResponseEntity<List<Cocktail>> {
        val cocktails = cocktailService.searchCocktailsByName(name)
        return ResponseEntity.ok(cocktails)
    }
}