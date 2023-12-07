package day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readInput

class Day7Test {

    @Test
    fun `part 1 - example input`() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()

        val hands = parseHands(input)
        assertThat(hands.winnings).isEqualTo(6440)
    }

    @Test
    fun `part 1 - sorting`() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()

        val handsCollection = parseHands(input)

        val sortedHands = handsCollection.hands.sorted()

        assertThat(sortedHands).containsExactly(
            parseHand("32T3K 765"),
            parseHand("KTJJT 220"),
            parseHand("KK677 28"),
            parseHand("T55J5 684"),
            parseHand("QQQJA 483"),
        )
    }

    @Test
    fun `part 1 - puzzle input`() {
        val hands = parseHands(readInput(7))

        assertThat(hands.winnings).isEqualTo(253638586)
    }
}