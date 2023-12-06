package day6

import ceilOrIncrement
import floorOrDecrement
import java.math.MathContext

data class Race(private val raceTime: Long, private val recordDistance: Long) {
    val chargeTimesBeatingRecord: LongRange
        get() {
            val Tr = raceTime.toBigDecimal()
            val D = recordDistance
            // Tc is charge time
            //
            // We want to solve the inequality:
            // (Tr - Tc) * Tc > D
            // Tr * Tc - Tc^2 > D
            // Tc^2 - Tr * Tc - D < 0
            //
            // This is a quadratic equation, so we can use (-b ± sqrt(b^2 - 4ac)) / 2a to find zero points

            val mathContext = MathContext(10)

            val lowerZeroPoint = (Tr - (Tr.pow(2) - 4.toBigDecimal() * D.toBigDecimal()).sqrt(mathContext)) / 2.toBigDecimal()
            val upperZeroPoint = (Tr + (Tr.pow(2) - 4.toBigDecimal() * D.toBigDecimal()).sqrt(mathContext)) / 2.toBigDecimal()

            // If lower- or upperZeroPoint is an integer, then it will match the record for that chargeTime.
            // These should not be included, as we are only interested in breaking the record.

            return lowerZeroPoint.ceilOrIncrement()..upperZeroPoint.floorOrDecrement()
        }
}

/**
 * Receives (e.g.)
 * "Time:      7  15   30
 *  Distance:  9  40  200"
 */
fun parseRaces(input: String): List<Race> {
    val parsedLines = input.lines().map { line ->
        line
            .split(":")[1]
            .split(" ")
            .filterNot { it.isEmpty() }
            .map { it.toLong() }
    }

    return parsedLines[0].zip(parsedLines[1])
        .map { (raceTime, recordDistance) -> Race(raceTime, recordDistance) }
}

/**
 * Receives (e.g.)
 * "Time:      7  15   30
 *  Distance:  9  40  200"
 */
fun parseRaceIgnoringKerning(input: String): Race {
    val (raceTime, recordDistance) = input.lines().map { line ->
        line
            .split(":")[1]
            .replace(" ", "")
            .toLong()
    }

    return Race(raceTime, recordDistance)
}