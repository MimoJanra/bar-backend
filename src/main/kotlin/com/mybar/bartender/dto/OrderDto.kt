package com.mybar.bartender.dto

class OrderDto(
    barId : Long,
    orderItems : List<OrderItemDto>,
    note : String,
)