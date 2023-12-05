package day5

import ext.difference
import ext.intersect
import ext.overlaps

data class Almanac(private val seeds: List<Long>, private val maps: List<CategoryMap>) {
    val lowestLocationNumber: Long
        get() = seeds
            .map { seed -> maps.fold(seed) { item, categoryMap -> categoryMap.map(item) } }
            .min()
}

data class RangedAlmanac(private val seedRanges: List<LongRange>, private val maps: List<CategoryMap>) {
    val lowestLocationNumber: Long
        get() = maps
            .fold(seedRanges) { itemRanges, categoryMap -> categoryMap.map(itemRanges) }
            .minBy { it.first }
            .first
}

data class CategoryMap(
    private val source: String,
    private val destination: String,
    private val ranges: List<SourceToDestinationRange>
) {
    fun map(item: Long): Long {
        return ranges
            .find { item in it }?.map(item)
            ?: item
    }

    fun map(itemRanges: List<LongRange>): List<LongRange> {
        val mappedRanges = mutableListOf<LongRange>()
        var notMappedRanges = itemRanges

        ranges.forEach { mappingRange ->
            val mappingResult = mappingRange.map(notMappedRanges)
            mappedRanges.addAll(mappingResult.mappedRanges)
            notMappedRanges = mappingResult.notMappedRanges
        }

        return mappedRanges + notMappedRanges
    }
}

data class MappingResult(val mappedRanges: List<LongRange>, val notMappedRanges: List<LongRange>)

data class SourceToDestinationRange(
    private val destinationRangeStart: Long,
    private val sourceRangeStart: Long,
    private val rangeLength: Long
) {
    private val sourceRange = sourceRangeStart..<sourceRangeStart + rangeLength
    private val sourceToDestinationRangeDelta = destinationRangeStart - sourceRangeStart

    operator fun contains(sourceItem: Long): Boolean {
        return sourceItem in sourceRange
    }

    fun map(sourceItem: Long): Long {
        if (sourceItem !in sourceRange) {
            throw IllegalArgumentException("Given sourceItem ($sourceItem) is not in source range ($sourceRange) for this SourceToDestinationRange")
        }
        return sourceItem + sourceToDestinationRangeDelta
    }

    fun map(ranges: List<LongRange>): MappingResult {
        val mappingResults = ranges.map(::map)
        return MappingResult(
            mappedRanges = mappingResults.flatMap { it.mappedRanges },
            notMappedRanges = mappingResults.flatMap { it.notMappedRanges }
        )
    }

    private fun map(range: LongRange): MappingResult {
        return if (sourceRange.overlaps(range)) {
            val mappedOverlappingRange = sourceRange.intersect(range)
                ?.let { it.first + sourceToDestinationRangeDelta..it.last + sourceToDestinationRangeDelta }
            val notOverlappingRanges = sourceRange.difference(range)

            MappingResult(
                mappedRanges = listOfNotNull(mappedOverlappingRange),
                notMappedRanges = notOverlappingRanges
            )
        } else {
            MappingResult(
                mappedRanges = emptyList(),
                notMappedRanges = listOf(range)
            )
        }
    }
}