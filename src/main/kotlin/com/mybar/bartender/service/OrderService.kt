package com.mybar.bartender.service

import com.mybar.bartender.dto.OrderDto
import com.mybar.bartender.model.Order
import com.mybar.bartender.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService(@Autowired val orderRepository: OrderRepository) {

    fun createOrder(orderDto: OrderDto): Order {
        return TODO("Provide the return value")
    }

    fun getOrdersByUserId(userId: Long): List<Order> {
        return orderRepository.findByUserId(userId)
    }
}