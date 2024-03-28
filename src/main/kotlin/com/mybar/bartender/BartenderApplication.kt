package com.mybar.bartender

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.annotation.EnableRetry

@SpringBootApplication
@EnableRetry
class BartenderApplication

fun main(args: Array<String>) {
    runApplication<BartenderApplication>(*args)
}
