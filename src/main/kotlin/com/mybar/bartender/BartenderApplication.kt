package com.mybar.bartender

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BartenderApplication

fun main(args: Array<String>) {
    runApplication<BartenderApplication>(*args)
}
