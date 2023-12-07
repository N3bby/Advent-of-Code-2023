package day7

data class Hand(private val cards: List<Card>, private val bid: Int, private val jokersAreWildcards: Boolean) : Comparable<Hand> {

    val type = HandType.getHandType(cards, jokersAreWildcards)

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

enum class HandType(
    val strength: Int,
    private val predicate: (cardGroups: Map<Card, List<Card>>, jokers: Int) -> Boolean
) {
    FIVE_OF_A_KIND(6, { cardGroups, jokers ->
        cardGroups.any { it.value.size + jokers == 5 } || jokers == 5
    }),
    FOUR_OF_A_KIND(5, { cardGroups, jokers ->
        cardGroups.any { it.value.size + jokers == 4 }
    }),
    FULL_HOUSE(4, { cardGroups, jokers ->
        // Only case where we can use a joker for creating a full house is when we have two pairs and adding the joker to 1 of them
        // Otherwise, it should be a FOUR_OF_A_KIND
        if(jokers != 0 && TWO_PAIR.predicate(cardGroups, 0)) {
            cardGroups.any { it.value.size + jokers == 3 } && cardGroups.any { it.value.size == 2 }
        } else {
            cardGroups.any { it.value.size == 3 } && cardGroups.any { it.value.size == 2 }
        }
    }),
    THREE_OF_A_KIND(3, { cardGroups, jokers ->
        cardGroups.any { it.value.size + jokers == 3 }
    }),
    TWO_PAIR(2, { cardGroups, _ ->
        // It doesn't make sense to create a 2-pair if you have a joker since THREE_OF_A_KIND and up are better hands
        // This means we can ignore that case
        cardGroups.count { it.value.size == 2 } == 2
    }),
    ONE_PAIR(1, { cardGroups, jokers ->
        cardGroups.any { it.value.size + jokers == 2 }
    }),
    HIGH_CARD(0, { _, _ -> true });

    companion object {
        fun getHandType(cards: List<Card>, jokersAreWildcards: Boolean): HandType {
            return if (jokersAreWildcards) {
                val jokers = cards.count { it.isJoker() }
                val cardGroups = cards.filterNot { it.isJoker() }.groupBy { it }
                entries.first { it.predicate(cardGroups, jokers) }
            } else {
                val cardGroups = cards.groupBy { it }
                entries.first { it.predicate(cardGroups, 0) }
            }
        }
    }
}