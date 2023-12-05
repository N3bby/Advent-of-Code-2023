package ext

fun LongRange.contains(other: LongRange): Boolean {
    return this.contains(other.first) && this.contains(other.last)
}

fun LongRange.overlaps(other: LongRange): Boolean {
    return this.contains(other.first) || this.contains(other.last) || other.contains(this.first) || other.contains(this.last)
}

fun LongRange.intersect(other: LongRange): LongRange? {
    if (!overlaps(other)) return null
    if (contains(other)) return other

    val overlapsLowerEnd = other.first < first && other.last <= last
    return if (overlapsLowerEnd) {
        first..other.last
    } else {
        other.first..last
    }
}

fun LongRange.difference(other: LongRange): List<LongRange> {
    if (contains(other)) return emptyList()
    if (!overlaps(other)) return listOf(other)

    if (other.contains(this)) {
        return if (other.last == last) {
            listOf(other.first..<first)
        } else if (other.first == first) {
            listOf(last + 1..other.last)
        } else {
            listOf(other.first..<first, last + 1..other.last)
        }
    }

    val overlapsLowerEnd = other.first < first && other.last <= last
    return if (overlapsLowerEnd) {
        listOf(other.first..<first)
    } else {
        listOf(last + 1..other.last)
    }
}
