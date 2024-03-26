package com.mybar.bartender.model

import jakarta.persistence.*

@Entity
@Table(name = "roles")
class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = 0,

    @Column(name = "name")
    val name: String? = null
) {
}