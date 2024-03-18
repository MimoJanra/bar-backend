package com.mybar.bartender.model

import com.mybar.bartender.dto.BarDto
import com.mybar.bartender.model.cocktails.Cocktail
import jakarta.persistence.*

@Entity
@Table(name = "bars")
class Bar(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val location: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @OneToMany(mappedBy = "bar")
    val cocktails: Set<Cocktail> = HashSet(),
) {
    fun toDto(): BarDto {
        return BarDto(id = this.id!!, name = this.name, location = this.location)
    }
}