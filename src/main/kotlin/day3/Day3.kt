package day3

data class Bounds(val x: IntRange, val y: IntRange)
data class Position(val x: Int, val y: Int)

data class EngineNumber(val bounds: Bounds, val value: Int) {
    fun isCloseToSymbol(symbols: List<EngineSymbol>) {
        TODO()
    }
}

data class EngineSymbol(val position: Position)

fun parseNumbers(input: String): List<EngineNumber> {
    TODO()
}

fun parseSymbols(input: String): List<EngineSymbol> {
    TODO()
}
