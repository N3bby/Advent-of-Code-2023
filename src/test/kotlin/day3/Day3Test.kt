package day3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readInput

class Day3Test {

    @Test
    fun `parseLine - returns lists of numbers and list of symbols`() {
        val (numbers, symbols) = parseLine("467..114..$.", 1)

        assertThat(numbers).isEqualTo(listOf(
            EngineNumber(Bounds(0 .. 2, 1..1), 467),
            EngineNumber(Bounds(5 .. 7, 1..1), 114),
        ))
        assertThat(symbols).isEqualTo(listOf(
            EngineSymbol(Position(10, 1), '$')
        ))
    }

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

        val schematic = parseEngineSchematic(input)

        assertThat(schematic.sumOfPartNumbers).isEqualTo(4361)
    }

    @Test
    fun `part 1 - puzzle input`() {
        val input = readInput(3)
        val schematic = parseEngineSchematic(input)

        assertThat(schematic.sumOfPartNumbers).isEqualTo(522726)
    }

    @Test
    fun `part 2 - example input`() {
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

        val schematic = parseEngineSchematic(input)

        assertThat(schematic.sumOfGearRatios).isEqualTo(467835)
    }

    @Test
    fun `part 2 - puzzle input`() {
        val input = readInput(3)
        val schematic = parseEngineSchematic(input)

        assertThat(schematic.sumOfGearRatios).isEqualTo(81721933)
    }
}
