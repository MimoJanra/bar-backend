package com.mybar.bartender.model

import com.mybar.bartender.model.cocktails.Cocktail
import com.mybar.bartender.model.cocktails.Order
import jakarta.persistence.*

@Entity
@Table(name = "order_item")
class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    val order: Order,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cocktail_id", nullable = false)
    val cocktail: Cocktail,

    @Column()
    val count: Int,
)