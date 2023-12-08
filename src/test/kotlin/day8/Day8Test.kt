package day8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readInput

class Day8Test {

    @Test
    fun `part 1 - example input (1)`() {
        val input = """
            RL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()

        val network = parseNetwork(input)
        val steps = network.countNodesToTraverse()

        assertThat(steps).isEqualTo(2)
    }

    @Test
    fun `part 1 - example input (2)`() {
        val input = """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()

        val network = parseNetwork(input)
        val steps = network.countNodesToTraverse()

        assertThat(steps).isEqualTo(6)
    }

    @Test
    fun `part 1 - puzzle input`() {
        val network = parseNetwork(readInput(8))

        val steps = network.countNodesToTraverse()

        assertThat(steps).isEqualTo(18023)
    }

    @Test
    fun `part 2 - example input`() {
        val input = """
            LR

            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
        """.trimIndent()

        val network = parseNetwork(input)
        val steps = network.countNodesToTraverseAsGhost()

        assertThat(steps).isEqualTo(6)
    }

    @Test
    fun `part 2 - puzzle input`() {
        val network = parseNetwork(readInput(8))

        val steps = network.countNodesToTraverseAsGhost()

        assertThat(steps).isEqualTo(14449445933179L)
    }
}