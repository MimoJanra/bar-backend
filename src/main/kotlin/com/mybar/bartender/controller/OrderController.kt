package com.mybar.bartender.controller

import com.mybar.bartender.dto.OrderDto
import com.mybar.bartender.model.Order
import com.mybar.bartender.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    fun createOrder(@RequestBody orderDto: OrderDto): ResponseEntity<Any> {
        val order = orderService.createOrder(orderDto)
        val location = URI.create("/orders/${order.id}")
        return ResponseEntity.created(location).body(order)
    }

    @GetMapping("/user/{userId}")
    fun getOrdersByUserId(@PathVariable userId: Long): ResponseEntity<List<Order>> {
        val orders = orderService.getOrdersByUserId(userId)
        return ResponseEntity.ok(orders)
    }
}
