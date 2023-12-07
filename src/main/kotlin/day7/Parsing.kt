package day7

fun parseHands(input: String, jokersAreWildcards: Boolean = false): HandCollection {
    return HandCollection(input.lines().map { parseHand(it, jokersAreWildcards) })
}

fun parseHand(line: String, jokersAreWildcards: Boolean = false): Hand {
    val tokens = line.split(" ")

    val cards = tokens[0].toCharArray().map { card -> Card(card, jokersAreWildcards) }
    val bid = tokens[1].toInt()

    return Hand(cards, bid, jokersAreWildcards)
}