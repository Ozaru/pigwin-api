package com.github.ozaru.pigwin_api.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Movimento(
    @Id
    var id: String,
    @Column
    var descricao: String,
)
