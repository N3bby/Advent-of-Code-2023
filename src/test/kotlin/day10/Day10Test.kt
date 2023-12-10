package day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readInput

class Day10Test {

    @Test
    fun `part 1 - example input (simple)`() {
        val input = """
            .....
            .S-7.
            .|.|.
            .L-J.
            .....
        """.trimIndent()

        val sketch = parseSketch(input)
        val loop = sketch.findLoop()

        assertThat(loop.furthestDistanceFromStart).isEqualTo(4)
    }

    @Test
    fun `part 1 - example input (advanced)`() {
        val input = """
            7-F7-
            .FJ|7
            SJLL7
            |F--J
            LJ.LJ
        """.trimIndent()

        val sketch = parseSketch(input)
        val loop = sketch.findLoop()

        assertThat(loop.furthestDistanceFromStart).isEqualTo(8)
    }

    @Test
    fun `part 1 - puzzle input`() {
        val sketch = parseSketch(readInput(10))
        val loop = sketch.findLoop()

        assertThat(loop.furthestDistanceFromStart).isEqualTo(6856)
    }
}