package com.mybar.bartender.controller.cocktails

import com.mybar.bartender.dto.CocktailDto
import com.mybar.bartender.model.cocktails.Cocktail
import com.mybar.bartender.service.cocktails.CocktailCreationService
import com.mybar.bartender.service.coctails.CocktailService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/cocktails")
class CocktailController(
    private val cocktailService: CocktailService,
    private val cocktailCreationService: CocktailCreationService
) {

    @Operation(summary = "Get all cocktails", description = "Retrieve a list of all available cocktails")
    @GetMapping
    fun getAllCocktails(): ResponseEntity<List<CocktailDto>> {
        val cocktails = cocktailService.findAllCocktails().map { x -> x.toDto() }
        return ResponseEntity.ok(cocktails)
    }

    @Operation(
        summary = "Get a cocktail by its ID",
        description = "Provide an ID to look up a specific cocktail",
        responses = [
            ApiResponse(
                responseCode = "200", description = "Successful operation",
                content = [Content(schema = Schema(implementation = Cocktail::class))]
            ),
            ApiResponse(responseCode = "404", description = "Cocktail not found")
        ]
    )
    @GetMapping("/{id}")
    fun getCocktailById(@Parameter(description = "ID value for the cocktail you need to retrieve") @PathVariable id: Long): ResponseEntity<Cocktail> {
        val cocktail = cocktailService.getCocktailById(id)
        return if (cocktail != null) ResponseEntity.ok(cocktail) else ResponseEntity.notFound().build()
    }

    @PostMapping
    @Operation(
        summary = "Create a new cocktail",
        description = "Creates a new cocktail with the provided details including tags, ingredients, and inventory items.",
        requestBody = RequestBody(content = [Content(schema = Schema(implementation = CocktailDto::class))]),
        responses = [
            ApiResponse(
                responseCode = "200", description = "Cocktail created successfully",
                content = [Content(schema = Schema(implementation = Cocktail::class))]
            ),
            ApiResponse(responseCode = "400", description = "Invalid input")
        ]
    )
    fun createCocktail(@org.springframework.web.bind.annotation.RequestBody cocktailDto: CocktailDto): ResponseEntity<Cocktail> {
        val savedCocktail = cocktailCreationService.createCocktail(cocktailDto)
        return ResponseEntity.ok(savedCocktail)
    }

    @Operation(
        summary = "Update an existing cocktail",
        description = "Update details of an existing cocktail by ID",
        responses = [
            ApiResponse(
                responseCode = "200", description = "Cocktail updated successfully",
                content = [Content(schema = Schema(implementation = Cocktail::class))]
            ),
            ApiResponse(responseCode = "404", description = "Cocktail not found")
        ]
    )
    @PutMapping("/{id}")
    fun updateCocktail(@PathVariable id: Long, @RequestBody cocktail: Cocktail): ResponseEntity<Cocktail> {
        val updatedCocktail = cocktailService.updateCocktail(id, cocktail)
        return if (updatedCocktail != null) ResponseEntity.ok(updatedCocktail) else ResponseEntity.notFound().build()
    }

    @Operation(
        summary = "Delete a cocktail",
        description = "Remove a cocktail from the collection by ID",
        responses = [
            ApiResponse(responseCode = "200", description = "Cocktail deleted successfully"),
            ApiResponse(responseCode = "404", description = "Cocktail not found")
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteCocktail(@PathVariable id: Long): ResponseEntity<Void> {
        //TODO:- cocktailService.deleteCocktail(id)
        return ResponseEntity.ok().build()
    }

    @Operation(
        summary = "Search cocktails by name",
        description = "Search for cocktails that match the provided name",
        responses = [
            ApiResponse(
                responseCode = "200", description = "Search completed",
                content = [Content(schema = Schema(implementation = Cocktail::class))]
            )
        ]
    )
    @GetMapping("/search")
    fun searchCocktailsByName(@RequestParam("name") name: String): ResponseEntity<Cocktail> {
        val cocktails = cocktailService.findCocktailByName(name)
        return ResponseEntity.ok(cocktails)
    }
}