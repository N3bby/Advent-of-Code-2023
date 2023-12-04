package day4

import java.lang.IllegalArgumentException
import kotlin.math.pow

data class Scratchcard(val id: Int, val winningNumbers: List<Int>, val numbers: List<Int>) {
    val matchingNumbers get() = winningNumbers.toSet().intersect(numbers.toSet()).size
    val points get() = 2.0.pow(matchingNumbers - 1).toInt()
}

data class ScratchcardCollection(private val scratchcards: List<Scratchcard>) {
    private val scratchcardsById = scratchcards.associateBy { it.id }

    val totalPoints get() = scratchcards.sumOf { it.points }

    val totalNumberOfScratchcards: Int get() {
        val scratchcardsToProcess = scratchcards.toMutableList()
        var itemToProcess = 0

        while (itemToProcess < scratchcardsToProcess.size) {
            val scratchcard = scratchcardsToProcess[itemToProcess]
            val idsOfCopiesToProcess = scratchcard.id + 1.. scratchcard.id + scratchcard.matchingNumbers
            scratchcardsToProcess.addAll(idsOfCopiesToProcess.map { scratchcardsById[it]!! })
            itemToProcess++
        }

        return scratchcardsToProcess.size
    }
}

fun parseScratchcards(input: List<String>): ScratchcardCollection {
    return ScratchcardCollection(input.map(::parseScratchcard))
}

/**
 * Receives "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
 */
fun parseScratchcard(line: String): Scratchcard {
    val regex = Regex("""Card\s+(\d+): (.*)\|(.*)""")
    val match = regex.matchEntire(line)
        ?: throw IllegalArgumentException("Could not match regex for line: \"$line\"")

    return Scratchcard(
        id = match.groupValues[1].toInt(),
        winningNumbers = match.groupValues[2].split(" ")
            .filter { it.isNotEmpty() }
            .map { it.toInt() },
        numbers = match.groupValues[3].split(" ")
            .filter { it.isNotEmpty() }
            .map { it.toInt() },
    )
}
