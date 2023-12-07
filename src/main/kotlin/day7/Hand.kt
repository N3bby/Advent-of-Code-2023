package day7

data class Hand(private val cards: List<Card>, private val bid: Int) : Comparable<Hand> {

    private val type = HandType.getHandType(cards)

    override fun compareTo(other: Hand): Int {
        val strengthDelta = this.type.strength - other.type.strength
        if (strengthDelta == 0) {
            return cards.zip(other.cards)
                .map { (card, otherCard) -> card.compareTo(otherCard) }
                .firstOrNull { it != 0 }
                ?: 0
        }
        return strengthDelta
    }

    fun getWinnings(rank: Int): Int {
        return rank * bid
    }

}

enum class HandType(val strength: Int, private val predicate: (cards: List<Card>) -> Boolean) {
    FIVE_OF_A_KIND(6, { cards -> cards.groupBy { it }.any { it.value.size == 5 } }),
    FOUR_OF_A_KIND(5, { cards -> cards.groupBy { it }.any { it.value.size == 4 } }),
    FULL_HOUSE(4, { cards ->
        val cardGroups = cards.groupBy { it }
        cardGroups.any { it.value.size == 3 } && cardGroups.any { it.value.size == 2 }
    }),
    THREE_OF_A_KIND(3, { cards -> cards.groupBy { it }.any { it.value.size == 3 } }),
    TWO_PAIR(2, { cards -> cards.groupBy { it }.count { it.value.size == 2 } == 2 }),
    ONE_PAIR(1, { cards -> cards.groupBy { it }.any { it.value.size == 2 } }),
    HIGH_CARD(0, { _ -> true });

    companion object {
        fun getHandType(cards: List<Card>) = entries.first { it.predicate(cards) }
    }
}