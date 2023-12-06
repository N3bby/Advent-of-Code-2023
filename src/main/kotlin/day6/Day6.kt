package day6

import ceilOrIncrement
import floorOrDecrement
import kotlin.math.pow
import kotlin.math.sqrt

data class Race(private val raceTime: Int, private val recordDistance: Int) {
    val chargeTimesBeatingRecord: IntRange get() {
        val Tr = raceTime.toDouble()
        val D = recordDistance

        // Tc is charge time
        //
        // We want to solve the inequality:
        // (Tr - Tc) * Tc > D
        // Tr * Tc - Tc^2 > D
        // Tc^2 - Tr * Tc - D < 0
        //
        // This is a quadractic equation, so we can use (-b ± sqrt(b^2 - 4ac)) / 2a to find zero points

        val lowerZeroPoint = (Tr - sqrt(Tr.pow(2) - 4 * D)) / 2.0
        val upperZeroPoint = (Tr + sqrt(Tr.pow(2) - 4 * D)) / 2.0

        // If lower- or upperZeroPoint is an integer, then it will match the record for that chargeTime.
        // These should not be included, as we are only interested in breaking the record.

        return lowerZeroPoint.ceilOrIncrement() .. upperZeroPoint.floorOrDecrement()
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
            .map { it.toInt() }
    }

    return parsedLines[0].zip(parsedLines[1])
        .map { (raceTime, recordDistance) -> Race(raceTime, recordDistance) }
}