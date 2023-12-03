package day3

import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    fun `part 1 - example input`() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """.trimIndent()

        val numbers: List<EngineNumber> = parseNumbers(input)
        val symbols: List<EngineSymbol> = parseSymbols(input)

    }
}