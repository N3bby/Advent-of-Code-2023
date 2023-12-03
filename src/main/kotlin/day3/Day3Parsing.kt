package day3

fun parseEngineSchematic(input: String): EngineSchematic {
    val parsedLines = input.lines().mapIndexed { index, line -> parseLine(line, index) }
    val numbers = parsedLines.flatMap { it.first }
    val symbols = parsedLines.flatMap { it.second }
    return EngineSchematic(numbers, symbols)
}

fun parseLine(line: String, row: Int): Pair<List<EngineNumber>, List<EngineSymbol>> {
    val numbers = mutableListOf<EngineNumber>()
    val symbols = mutableListOf<EngineSymbol>()

    var numberBuffer: String? = null
    var numberBufferIndex = 0

    fun readNumberBuffer(): EngineNumber {
        val engineNumber = EngineNumber(
            Bounds(numberBufferIndex..<numberBufferIndex + numberBuffer!!.length, row..row),
            numberBuffer!!.toInt()
        )
        numberBuffer = null
        return engineNumber
    }

    line.forEachIndexed { index, c ->
        if (c.isDigit()) {
            if (numberBuffer == null) {
                numberBuffer = c.toString()
                numberBufferIndex = index
            } else {
                numberBuffer += c
            }
        } else {
            if (numberBuffer != null) {
                numbers.add(readNumberBuffer())
            }
            if (c != '.') {
                symbols.add(EngineSymbol(Position(index, row), c))
            }
        }
    }
    if (numberBuffer != null) {
        numbers.add(readNumberBuffer())
    }

    return Pair(numbers, symbols)
}
