package day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readInput

class Day1Test {

    @Test
    fun `part 1 - example input`() {
        val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent()

        val result = getSumOfCalibrationValues(input.lines(), false);
        assertThat(result).isEqualTo(142)
    }

    @Test
    fun `part 1 - puzzle input`() {
        val result = getSumOfCalibrationValues(readInput(1).lines(), false)
        assertThat(result).isEqualTo(54450)
    }

    @Test
    fun `part 2 - example input`() {
        val input = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent()

        val result = getSumOfCalibrationValues(input.lines(), true);
        assertThat(result).isEqualTo(281)
    }

    @Test
    fun `part 2 - puzzle input`() {
        val result = getSumOfCalibrationValues(readInput(1).lines(), true)
        assertThat(result).isEqualTo(54265)
    }

}