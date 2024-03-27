package com.mybar.bartender.controller

import com.mybar.bartender.controller.cocktails.CocktailController
import com.mybar.bartender.dto.CocktailDto
import com.mybar.bartender.model.cocktails.Cocktail
import com.mybar.bartender.service.cocktails.CocktailCreationService
import com.mybar.bartender.service.coctails.CocktailService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.math.BigDecimal

@WebMvcTest(CocktailController::class)
class CocktailControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var cocktailService: CocktailService

    @MockBean
    private lateinit var cocktailCreationService: CocktailCreationService

/*    // Пример коктейля
    private val exampleCocktail = Cocktail(
        name = "Персиковый сауэр",
        rating = BigDecimal("3.4"),
        imagePath = "images/Персиковый_сауэр.jpg",
        bar =,
        cocktailTags =,
        cocktailIngredients =,
        cocktailInventories =,
        recipeSteps =
    )*/

    @Test
    fun `test getAllCocktails returns cocktails list`() {
       // `when`(cocktailService.findAllCocktails()).thenReturn(listOf(exampleCocktail))

        mockMvc.perform(get("/api/cocktails"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0]").exists())

        verify(cocktailService).findAllCocktails()
    }

    @Test
    fun `test getCocktailById returns cocktail`() {
        //`when`(cocktailService.getCocktailById(1L)).thenReturn(exampleCocktail)

        mockMvc.perform(get("/api/cocktails/{id}", 1L))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))

        verify(cocktailService).getCocktailById(1L)
    }

    @Test
    fun `test createCocktail returns created cocktail`() {
        //`when`(cocktailCreationService.createCocktail(any(CocktailDto::class.java))).thenReturn(exampleCocktail)
        val cocktailDtoJson = ""

            mockMvc.perform(post("/api/cocktails")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cocktailDtoJson))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())

        verify(cocktailCreationService).createCocktail(any(CocktailDto::class.java))
    }

    @Test
    fun `test updateCocktail returns updated cocktail`() {
       // `when`(cocktailService.updateCocktail(eq(1L), any(Cocktail::class.java))).thenReturn(exampleCocktail)

        mockMvc.perform(put("/api/cocktails/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(""))
            .andExpect(status().isOk)

        verify(cocktailService).updateCocktail(eq(1L), any(Cocktail::class.java))
    }

    @Test
    fun `test deleteCocktail returns success`() {
        doNothing().`when`(cocktailService).deleteCocktail(1L, anyLong())

        mockMvc.perform(delete("/api/cocktails/{id}", 1L))
            .andExpect(status().isOk)

        verify(cocktailService).deleteCocktail(1L, anyLong())
    }

    @Test
    fun `test searchCocktailsByName returns cocktails list`() {
       // `when`(cocktailService.searchCocktailsByName("Test")).thenReturn(listOf(exampleCocktail))

        mockMvc.perform(get("/api/cocktails/search").param("name", "Test"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0]").exists())

       // verify(cocktailService).searchCocktailsByName("Test")
    }
}
