package day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readInput

class Day2Test {

    private val bag = Bag(
        mapOf(
            Pair("red", 12),
            Pair("green", 13),
            Pair("blue", 14)
        )
    )

    @Test
    fun `part 1 - parse game`() {
        val input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
        val game = parseGame(input)

        assertThat(game).isEqualTo(
            Game(
                1,
                listOf(
                    GameSet(mapOf(Pair("blue", 3), Pair("red", 4))),
                    GameSet(mapOf(Pair("red", 1), Pair("green", 2), Pair("blue", 6))),
                    GameSet(mapOf(Pair("green", 2))),
                )
            )
        )
    }

    @Test
    fun `part 1 - example input`() {
        val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent()

        val result = getPossibleGames(
            parseGames(input.lines()),
            bag
        ).sumOf { it.id }

        assertThat(result).isEqualTo(8)
    }

    @Test
    fun `part 1 - puzzle input`() {
        val games = parseGames(readInput(2).lines())
        val result = getPossibleGames(games, bag).sumOf { it.id }

        assertThat(result).isEqualTo(2913)
    }

    @Test
    fun `part 2 - example input`() {
        val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent()
        val games = parseGames(input.lines())

        val sumOfPowers = games.sumOf { it.getGamePower() }

        assertThat(sumOfPowers).isEqualTo(2286)
    }

    @Test
    fun `part 2 - puzzle input`() {
        val games = parseGames(readInput(2).lines())
        val sumOfPowers = games.sumOf { it.getGamePower() }

        assertThat(sumOfPowers).isEqualTo(55593)
    }
}
