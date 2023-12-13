package day12

import kotlin.math.pow

typealias PotentialSpringRecord = String
typealias PossibleSpringRecord = String

data class DamagedSpringRecord(private val record: String, private val contiguousGroups: List<Int>) {

    private val isPossibleRegex: Regex by lazy {
        // e.g. for contiguousGroups 3,2,1: "^.*#{3}\.*#{2}\.*#{1}.*$"
        val groupsRegex = contiguousGroups
            .map { "#{$it}" }
            .joinToString("""\.+""")
        """^\.*$groupsRegex\.*$""".toRegex()
    }

    fun getPossibleSpringRecords(): List<PossibleSpringRecord> {
        return getPotentialSpringRecords().filter { isPossible(it) }
    }

    private fun getPotentialSpringRecords(): List<PotentialSpringRecord> {
        val damagedSpots = record.count { it == '?' }
        val amountOfOptions = (2.0).pow(damagedSpots).toInt()

        val options = mutableListOf<String>()
        for (optionAsNumber in 0..<amountOfOptions) {
            var processedBits = 0
            val optionAsBits = optionAsNumber.toUInt()
                .toString(radix = 2)
                .takeLast(damagedSpots)
                .padStart(damagedSpots, '0')
            val option = record.map { spot ->
                if(spot == '?') {
                    processedBits++
                    when {
                        optionAsBits[processedBits - 1] == '1' -> '#'
                        else -> '.'
                    }
                } else {
                    spot
                }
            }.joinToString("")
            options.add(option)
        }

        return options
    }

    private fun isPossible(potentialSpringRecord: PotentialSpringRecord): Boolean {
        return isPossibleRegex.matches(potentialSpringRecord)
    }
}

fun parseSpringRecords(input: String): List<DamagedSpringRecord> {
    return input.lines().map(::parseSpringRecord)
}

fun parseSpringRecord(line: String): DamagedSpringRecord {
    val (record, groups) = line.split(' ')
    return DamagedSpringRecord(record, groups.split(',').map { it.toInt() })
}
