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
        val expandedUniverse = universe.atAge(2)

        assertThat(expandedUniverse.sumOfDistancesBetweenGalaxies).isEqualTo(374)
    }

    @Test
    fun `part 1 - puzzle input`() {
        val universe = parseUniverse(readInput(11))
        val expandedUniverse = universe.atAge(2)

        assertThat(expandedUniverse.sumOfDistancesBetweenGalaxies).isEqualTo(9329143)
    }

    @Test
    fun `part 2 - example input`() {
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
        val expandedUniverse = universe.atAge(100)

        assertThat(expandedUniverse.sumOfDistancesBetweenGalaxies).isEqualTo(8410)
    }

    @Test
    fun `part 2 - puzzle input`() {
        val universe = parseUniverse(readInput(11))
        val expandedUniverse = universe.atAge(1_000_000)

        assertThat(expandedUniverse.sumOfDistancesBetweenGalaxies).isEqualTo(710674907809)
    }
}

