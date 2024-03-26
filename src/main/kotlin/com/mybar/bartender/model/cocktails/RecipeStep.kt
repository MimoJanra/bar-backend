package com.mybar.bartender.model.cocktails

import com.mybar.bartender.dto.RecipeStepDto
import jakarta.persistence.*

@Entity
@Table(name = "RecipeSteps")
data class RecipeStep(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "cocktail_id", nullable = false)
    val cocktail: Cocktail,

    @Column(name = "step_number", nullable = false)
    val stepNumber: Int,

    @Column(nullable = false, columnDefinition = "TEXT")
    val description: String
) {
    fun toDto(): RecipeStepDto {
        return RecipeStepDto(this.stepNumber, this.description);
    }
}