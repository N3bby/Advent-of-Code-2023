package day6

import ext.multiplicationOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readInput

class Day6Test {

    @Test
    fun `part 1 - example input`() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()

        val races = parseRaces(input)

        val result = races.multiplicationOf { it.chargeTimesBeatingRecord.count() }
        assertThat(result).isEqualTo(288)
    }

    @Test
    fun `part 1 - puzzle input`() {
        val races = parseRaces(readInput(6))

        val result = races.multiplicationOf { it.chargeTimesBeatingRecord.count() }

        assertThat(result).isEqualTo(211904)
    }

    @Test
    fun `part 2 - example input`() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()

        val race = parseRaceIgnoringKerning(input)

        assertThat(race.chargeTimesBeatingRecord.count()).isEqualTo(71503)
    }

    @Test
    fun `part 2 - puzzle input`() {
        val race = parseRaceIgnoringKerning(readInput(6))

        assertThat(race.chargeTimesBeatingRecord.count()).isEqualTo(0)
    }
}