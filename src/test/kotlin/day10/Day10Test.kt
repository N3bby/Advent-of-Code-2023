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

    @Test
    fun `part 1 - example input (very simple)`() {
        val input = """
            .....
            .S-7.
            .|.|.
            .L-J.
            .....
        """.trimIndent()

        val sketch = parseSketch(input)
        val loop = sketch.findLoop()

        assertThat(loop.findAmountOfTilesInsideLoop()).isEqualTo(1)
    }

    @Test
    fun `part 2 - example input (simple)`() {
        val input = """
            ...........
            .S-------7.
            .|F-----7|.
            .||.....||.
            .||.....||.
            .|L-7.F-J|.
            .|..|.|..|.
            .L--J.L--J.
            ...........
        """.trimIndent()

        val sketch = parseSketch(input)
        val loop = sketch.findLoop()

        assertThat(loop.findAmountOfTilesInsideLoop()).isEqualTo(4)
    }

    @Test
    fun `part 2 - example input (advanced)`() {
        val input = """
            .F----7F7F7F7F-7....
            .|F--7||||||||FJ....
            .||.FJ||||||||L7....
            FJL7L7LJLJ||LJ.L-7..
            L--J.L7...LJS7F-7L7.
            ....F-J..F7FJ|L7L7L7
            ....L7.F7||L7|.L7L7|
            .....|FJLJ|FJ|F7|.LJ
            ....FJL-7.||.||||...
            ....L---J.LJ.LJLJ...
        """.trimIndent()

        val sketch = parseSketch(input)
        val loop = sketch.findLoop()

        assertThat(loop.findAmountOfTilesInsideLoop()).isEqualTo(8)
    }

    @Test
    fun `part 2 - example input (advanced - with junk pipes)`() {
        val input = """
            FF7FSF7F7F7F7F7F---7
            L|LJ||||||||||||F--J
            FL-7LJLJ||||||LJL-77
            F--JF--7||LJLJ7F7FJ-
            L---JF-JLJ.||-FJLJJ7
            |F|F-JF---7F7-L7L|7|
            |FFJF7L7F-JF7|JL---7
            7-L-JL7||F7|L7F-7F7|
            L.L7LFJ|||||FJL7||LJ
            L7JLJL-JLJLJL--JLJ.L
        """.trimIndent()

        val sketch = parseSketch(input)
        val loop = sketch.findLoop()

        assertThat(loop.findAmountOfTilesInsideLoop()).isEqualTo(10)
    }

    @Test
    fun `part 2 - puzzle input`() {
        val sketch = parseSketch(readInput(10))
        val loop = sketch.findLoop()

        assertThat(loop.findAmountOfTilesInsideLoop()).isEqualTo(501)
    }
}