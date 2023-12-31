package day4

import java.lang.IllegalArgumentException
import java.util.LinkedList
import java.util.Queue
import kotlin.math.pow

data class Scratchcard(val id: Int, val winningNumbers: List<Int>, val numbers: List<Int>) {
    val matchingNumbers = winningNumbers.toSet().intersect(numbers.toSet()).size
    val points = 2.0.pow(matchingNumbers - 1).toInt()
}

data class ScratchcardCollection(private val scratchcards: List<Scratchcard>) {
    private val scratchcardsById = scratchcards.associateBy { it.id }

    val totalPoints get() = scratchcards.sumOf { it.points }

    val totalNumberOfScratchcards: Int get() {
        val scratchcardsToProcess: Queue<Scratchcard> = LinkedList(scratchcards)
        var scratchcardsProcessed = 0

        while (scratchcardsToProcess.peek() != null) {
            val scratchcard = scratchcardsToProcess.poll()
            val idsOfCopiesToProcess = scratchcard.id + 1.. scratchcard.id + scratchcard.matchingNumbers
            scratchcardsToProcess.addAll(idsOfCopiesToProcess.map { scratchcardsById[it]!! })
            scratchcardsProcessed++
        }

        return scratchcardsProcessed
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
