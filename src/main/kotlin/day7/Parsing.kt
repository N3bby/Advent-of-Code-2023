package day7

fun parseHands(input: String): HandCollection {
    return HandCollection(input.lines().map(::parseHand))
}

fun parseHand(line: String): Hand {
    val tokens = line.split(" ")

    val cards = tokens[0].toCharArray().map(::Card)
    val bid = tokens[1].toInt()

    return Hand(cards, bid)
}