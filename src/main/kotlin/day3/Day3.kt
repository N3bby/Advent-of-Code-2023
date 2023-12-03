package day3

import java.lang.IllegalArgumentException

data class EngineSymbol(val position: Position, val symbol: Char) {
    fun asGear(numbers: List<EngineNumber>): Gear? {
        if(symbol != '*') return null

        val adjacentNumbers = numbers.filter { it.bounds.expand(1).contains(position) }
        if(adjacentNumbers.size != 2) return null

        return Gear(position, adjacentNumbers)
    }
}

data class Gear(val position: Position, val adjacentNumbers: List<EngineNumber>) {
    init {
        if(adjacentNumbers.size != 2) throw IllegalArgumentException("A gear must have two adjacent part numbers")
    }

    val gearRatio get() = adjacentNumbers[0].value * adjacentNumbers[1].value
}

data class EngineNumber(val bounds: Bounds, val value: Int) {
    fun isAdjacentToAny(symbols: List<EngineSymbol>): Boolean {
        val expandedBounds = bounds.expand(1)
        return symbols.any { expandedBounds.contains(it.position) }
    }
}

data class EngineSchematic(val numbers: List<EngineNumber>, val symbols: List<EngineSymbol>) {
    private val partNumbers get() = numbers.filter { it.isAdjacentToAny(symbols) }
    private val gears get() = symbols.mapNotNull { it.asGear(numbers) }

    val sumOfPartNumbers get() = partNumbers.sumOf { it.value }
    val sumOfGearRatios get() = gears.sumOf { it.gearRatio }
}

