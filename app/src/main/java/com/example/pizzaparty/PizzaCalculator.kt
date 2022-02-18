package com.example.pizzaparty

import kotlin.math.ceil

const val SLICES_PER_PIZZA = 8

/**
 * A Pizza Calculator
 *
 * This class takes a party size and hunger level to calculate how many Pizzas
 * it will take to feed the party. Inherits from enum class HungerLevel
 *
 * @property Int the total Pizzas needed to feed the group.
 * @constructor creates an instance of PizzaCalculator with a party size and a hunger level.
 */
class PizzaCalculator(partySize: Int, var hungerLevel: HungerLevel) {
    var partySize = 0
        set(value) {
            field = if (value >= 0) value else 0
        }

    enum class HungerLevel(var numSlices: Int) {
        LIGHT(2), MEDIUM(3), RAVENOUS(4)
    }

    val totalPizzas: Int
        get() {
            return ceil(partySize * hungerLevel.numSlices / SLICES_PER_PIZZA.toDouble()).toInt()
        }

    init {
        this.partySize = partySize
    }
}