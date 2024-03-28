package com.mybar.bartender.controller

import org.springframework.dao.DataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource

@RestController
class HealthCheckController(private val dataSource: DataSource) {

    @GetMapping("/api/healthcheck")
    fun healthCheck(): ResponseEntity<String> {
        return try {
            dataSource.connection.use { conn ->
                val statement = conn.createStatement()
                statement.executeQuery("SELECT 1")
            }
            ResponseEntity.ok("OK")
        } catch (e: DataAccessException) {
            ResponseEntity.status(500).body("Database connection failed")
        }
    }
}