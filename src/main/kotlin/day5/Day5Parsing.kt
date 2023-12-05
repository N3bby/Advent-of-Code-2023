package day5

import java.lang.IllegalArgumentException

fun parseAlmanac(input: String): Almanac {
    val categories = input.split("\n\n")

    val seeds = parseSeeds(categories[0])
    val categoryMaps = categories.drop(1).map(::parseCategoryMap)

    return Almanac(seeds, categoryMaps)
}

fun parseRangedAlmanac(input: String): RangedAlmanac {
    val categories = input.split("\n\n")

    val seeds = parseSeedsAsRanges(categories[0])
    val categoryMaps = categories.drop(1).map(::parseCategoryMap)

    return RangedAlmanac(seeds, categoryMaps)
}

/**
 * Receives (e.g.) "seeds: 79 14 55 13"
 */
fun parseSeeds(input: String): List<Long> {
    return input.split(':')[1].trim().split(' ').map { it.toLong() }
}

/**
 * Receives (e.g.) "seeds: 79 14 55 13"
 */
fun parseSeedsAsRanges(input: String): List<LongRange> {
    return input.split(':')[1].trim()
        .split(' ')
        .chunked(2)
        .map { chunk ->
            val rangeStart = chunk[0].toLong()
            val rangeLength = chunk[1].toLong()
            rangeStart..<rangeStart + rangeLength
        }
}

/**
 * Receives (e.g.)
 * "seed-to-soil map:
 *  50 98 2
 *  52 50 48"
 */
fun parseCategoryMap(input: String): CategoryMap {
    val lines = input.split("\n")

    val matchResult = Regex("(.*)-to-(.*) map:").matchEntire(lines[0])
        ?: throw IllegalArgumentException("Could not parse first line of category map: \"$lines[0]\"")

    val source = matchResult.groupValues[1]
    val destination = matchResult.groupValues[2]
    val ranges = lines.drop(1).map(::parseRange)

    return CategoryMap(source, destination, ranges)
}

/**
 * Receives (e.g.) "52 50 48"
 */
fun parseRange(line: String): SourceToDestinationRange {
    val tokens = line.split(" ")

    val destinationRangeStart = tokens[0].toLong()
    val sourceRangeStart = tokens[1].toLong()
    val rangeLength = tokens[2].toLong()

    return SourceToDestinationRange(destinationRangeStart, sourceRangeStart, rangeLength)
}