package com.mybar.bartender.model.cocktails

import com.mybar.bartender.model.Bar
import com.mybar.bartender.model.User
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
)