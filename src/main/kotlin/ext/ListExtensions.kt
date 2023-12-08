package ext

fun <T> List<T>.steppedBy(step: Int, offset: Int = 0): List<T> {
    return this.filterIndexed { index, _ -> index % step == offset }
}

fun List<Int>.multiplication(): Int = reduce { acc, number -> acc * number }
fun List<Long>.multiplication(): Long = reduce { acc, number -> acc * number }

fun <T> List<T>.multiplicationOf(block: (T) -> Int): Int = this.map(block).multiplication()