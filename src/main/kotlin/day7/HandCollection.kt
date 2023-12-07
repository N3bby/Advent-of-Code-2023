package day7

data class HandCollection(val hands: List<Hand>) {
    val winnings: Int
        get() = hands
            .sorted()
            .mapIndexed { index, hand -> hand.getWinnings(index + 1) }
            .sum()
}

