package day7

import day7.HandType.FULL_HOUSE
import day7.HandType.THREE_OF_A_KIND
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

    @Test
    fun `part 1 - full house`() {
        val hand = parseHand("97Q99 26", false)
        assertThat(hand.type).isEqualTo(THREE_OF_A_KIND)
    }

    @Test
    fun `part 2 - example input`() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()

        val hands = parseHands(input, true)

        assertThat(hands.winnings).isEqualTo(5905)
    }

    @Test
    fun `part 2 - jokers should not be added to jokers`() {
        val hand = parseHand("JJ234 10", true)
        assertThat(hand.type).isEqualTo(THREE_OF_A_KIND)
    }

    @Test
    fun `part 2 - create full house with a joker`() {
        val hand = parseHand("J2255 10", true)
        assertThat(hand.type).isEqualTo(FULL_HOUSE)
    }

    @Test
    fun `part 2 - three of a kind (with 1 joker) should not accidentally become a full house`() {
        val hand = parseHand("J5523 10", true)
        assertThat(hand.type).isEqualTo(THREE_OF_A_KIND)
    }

    @Test
    fun `part 2 - puzzle input`() {
        val hands = parseHands(readInput(7), true)

        assertThat(hands.winnings).isEqualTo(0)
    }
}