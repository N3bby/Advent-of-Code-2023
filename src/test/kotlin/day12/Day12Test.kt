package day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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

        assertThat(springRecords.sumOfPossibleArrangements).isEqualTo(21)
    }

}

