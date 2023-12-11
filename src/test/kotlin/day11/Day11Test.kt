package day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readInput

class Day11Test {

    @Test
    fun `part 1 - example input`() {
        val input = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
        """.trimIndent()

        val universe = parseUniverse(input)
        val expandedUniverse = universe.expand()

        assertThat(expandedUniverse.sumOfDistancesBetweenGalaxies).isEqualTo(374)
    }

    @Test
    fun `part 1 - puzzle input`() {
        val universe = parseUniverse(readInput(11))
        val expandedUniverse = universe.expand()

        assertThat(expandedUniverse.sumOfDistancesBetweenGalaxies).isEqualTo(9329143)
    }
}

