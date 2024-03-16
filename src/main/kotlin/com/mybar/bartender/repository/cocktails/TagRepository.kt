package com.mybar.bartender.repository.cocktails

import com.mybar.bartender.model.cocktails.CocktailTag
import com.mybar.bartender.model.cocktails.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface TagRepository : JpaRepository<Tag, Long> {
    fun findByName(name: String): Tag?
    abstract fun save(cocktailTag: CocktailTag)
}