package day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readInput

class Day5Test {

    @Test
    fun `part 1 - example input`() {
        val input = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48

            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15

            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4

            water-to-light map:
            88 18 7
            18 25 70

            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13

            temperature-to-humidity map:
            0 69 1
            1 0 69

            humidity-to-location map:
            60 56 37
            56 93 4
        """.trimIndent()

        val almanac = parseAlmanac(input)

        assertThat(almanac.lowestLocationNumber).isEqualTo(35)
    }

    @Test
    fun `part 1 - puzzle input`() {
        val input = readInput(5)

        val almanac = parseAlmanac(input)

        assertThat(almanac.lowestLocationNumber).isEqualTo(910845529)
    }

    @Test
    fun `part 2 - CategoryMap - maps entire range`() {
        val categoryMap = CategoryMap("seed", "soil", listOf(
            SourceToDestinationRange(30, 20, 10), // Should be ignored
            SourceToDestinationRange(60, 10, 5),
        ))

        val result = categoryMap.map(listOf(11L..14L))

        assertThat(result).isEqualTo(listOf(61L..64L))
    }

    @Test
    fun `part 2 - CategoryMap - maps part of input range`() {
        val categoryMap = CategoryMap("seed", "soil", listOf(
            SourceToDestinationRange(30, 20, 10), // Should be ignored
            SourceToDestinationRange(60, 10, 5),
        ))

        val result = categoryMap.map(listOf(5L..14L))

        assertThat(result).isEqualTo(listOf(
            60L..64L,
            5L..9L,
        ))
    }

    @Test
    fun `part 2 - example input`() {
        val input = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48

            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15

            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4

            water-to-light map:
            88 18 7
            18 25 70

            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13

            temperature-to-humidity map:
            0 69 1
            1 0 69

            humidity-to-location map:
            60 56 37
            56 93 4
        """.trimIndent()

        val almanac = parseRangedAlmanac(input)

        assertThat(almanac.lowestLocationNumber).isEqualTo(46)
    }

    @Test
    fun `part 2 - puzzle input`() {
        val input = readInput(5)

        val almanac = parseRangedAlmanac(input)

        assertThat(almanac.lowestLocationNumber).isEqualTo(0)
    }
}