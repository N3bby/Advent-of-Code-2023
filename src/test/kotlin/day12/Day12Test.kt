package day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readInput

class Day12Test {

    @Test
    fun `part 1 - example input`() {
        val input = """
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1
        """.trimIndent()

        val springRecords = parseSpringRecords(input)
        val possibleArrangements = springRecords.map { it.getPossibleSpringRecords().size }

        assertThat(possibleArrangements).containsExactly(1, 4, 1, 1, 4, 10)
        assertThat(possibleArrangements.sum()).isEqualTo(21)
    }

    @Test
    fun `part 1 - puzzle input`() {
        val springRecords = parseSpringRecords(readInput(12))

        val possibleArrangements = springRecords.sumOf { it.getPossibleSpringRecords().size }
        assertThat(possibleArrangements).isEqualTo(7350)
    }
}

