package day7

fun parseHands(input: String, considerJokers: Boolean = false): HandCollection {
    return HandCollection(input.lines().map { parseHand(it, considerJokers) })
}

fun parseHand(line: String, considerJokers: Boolean = false): Hand {
    val tokens = line.split(" ")

    val cards = tokens[0].toCharArray().map { card -> Card(card, considerJokers) }
    val bid = tokens[1].toInt()

    return Hand(cards, bid, considerJokers)
}