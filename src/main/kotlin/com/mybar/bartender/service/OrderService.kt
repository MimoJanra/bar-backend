package com.mybar.bartender.service

import com.mybar.bartender.dto.OrderDto
import com.mybar.bartender.model.Order
import com.mybar.bartender.model.OrderItem
import com.mybar.bartender.model.OrderStatus
import com.mybar.bartender.repository.OrderRepository
import com.mybar.bartender.repository.cocktails.CocktailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService(@Autowired val orderRepository: OrderRepository,
                   @Autowired val cocktailRepository: CocktailRepository
) {

    fun createOrder(orderDto: OrderDto): Order {
        val order = Order(
            userId = orderDto.userId,
            barId = orderDto.barId,
            note = orderDto.note,
            totalCost = orderDto.totalCost,
            status = OrderStatus.valueOf(orderDto.status.name),
            createdAt = orderDto.createdAt
        )

        val savedOrder = orderRepository.save(order)

        val orderItems = orderDto.orderItems.map { itemDto ->
            val cocktail = cocktailRepository.findById(itemDto.cocktailId)
                .orElseThrow { Exception("Cocktail not found with id: ${itemDto.cocktailId}") }

            OrderItem(
                order = savedOrder,
                cocktail = cocktail,
                count = itemDto.count
            )
        }.toMutableSet()

        savedOrder.orderItems = orderItems
        return orderRepository.save(savedOrder)
    }

    fun getOrdersByUserId(userId: Long): List<Order> {
        return orderRepository.findByUserId(userId)
    }
}