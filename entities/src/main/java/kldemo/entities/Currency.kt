package kldemo.entities

import java.math.BigDecimal

data class Currency (
    val name: String,
    var rate: BigDecimal)