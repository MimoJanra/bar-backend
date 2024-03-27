package com.mybar.bartender.model.cocktails

import com.mybar.bartender.dto.CocktailDto
import com.mybar.bartender.model.Bar
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "Cocktails")
class Cocktail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 255)
    var name: String,

    @Column(precision = 2, scale = 1)
    var rating: BigDecimal? = null,

    @Column(name = "image_path", length = 255)
    var imagePath: String? = null,

    @ManyToOne
    @JoinColumn(name = "bar_id", nullable = false)
    val bar: Bar,

    @OneToMany(mappedBy = "cocktail")
    val cocktailTags: Set<CocktailTag> = HashSet(),

    @OneToMany(mappedBy = "cocktail", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val cocktailIngredients: Set<CocktailIngredient> = HashSet(),

    @OneToMany(mappedBy = "cocktail", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val cocktailInventories: Set<CocktailInventory> = HashSet(),

    @OneToMany(mappedBy = "cocktail", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val recipeSteps: Set<RecipeStep> = HashSet()
) {
    fun toDto(): CocktailDto {
        return CocktailDto(
            this.id,
            this.name,
            this.rating!!,
            this.imagePath,
            this.cocktailTags.map { x -> x.tag.name },
            this.cocktailIngredients.map { x -> x.toDto() },
            this.cocktailInventories.map { x -> x.toDto() },
            this.recipeSteps.map { x -> x.toDto() },
            this.bar.id!!
        )
    }
}