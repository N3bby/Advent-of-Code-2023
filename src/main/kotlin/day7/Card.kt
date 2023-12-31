package day7

val CARD_STRENGTH_ORDER = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2').reversed()
val CARD_STRENGTH_ORDER_JOKERS_WILDCARDS = listOf('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J').reversed()

data class Card(private val card: Char, private val jokersAreWildcards: Boolean) : Comparable<Card> {

    private val strength by lazy {
        (if (jokersAreWildcards) CARD_STRENGTH_ORDER_JOKERS_WILDCARDS else CARD_STRENGTH_ORDER).indexOf(card)
    }

    fun isJoker(): Boolean {
        return card == 'J'
    }

    override fun compareTo(other: Card): Int {
        return strength - other.strength
    }

    override fun toString(): String {
        return card.toString()
    }

}