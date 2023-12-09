package day9

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readInput

class Day9Test {

    @Test
    fun `part 1 - example input`() {
        val input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent()

        val report = parseOasisReport(input)

        assertThat(report.sumOfFutureExtrapolatedHistories).isEqualTo(114)
    }

    @Test
    fun `part 1 - puzzle input`() {
        val report = parseOasisReport(readInput(9))

        assertThat(report.sumOfFutureExtrapolatedHistories).isEqualTo(1684566095)
    }

    @Test
    fun `part 2 - example input`() {
        val input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent()

        val report = parseOasisReport(input)

        assertThat(report.sumOfPastExtrapolatedHistories).isEqualTo(2)
    }

    @Test
    fun `part 2 - puzzle input`() {
        val report = parseOasisReport(readInput(9))

        assertThat(report.sumOfPastExtrapolatedHistories).isEqualTo(1136)
    }

}
