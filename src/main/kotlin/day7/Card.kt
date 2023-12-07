package day7

val CARD_STRENGTH_ORDER = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2').reversed()

data class Card(private val card: Char) : Comparable<Card> {

    private val strength by lazy { CARD_STRENGTH_ORDER.indexOf(card) }

    override fun compareTo(other: Card): Int {
        return strength - other.strength
    }

    override fun toString(): String {
        return card.toString()
    }

}