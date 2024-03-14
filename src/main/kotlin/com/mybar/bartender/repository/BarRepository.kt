package com.mybar.bartender.repository

import com.mybar.bartender.model.Bar
import org.springframework.data.jpa.repository.JpaRepository

interface BarRepository : JpaRepository<Bar, Long> {
}