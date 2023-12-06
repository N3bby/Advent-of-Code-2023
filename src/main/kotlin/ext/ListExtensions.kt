package ext

fun <T> List<T>.steppedBy(step: Int, offset: Int = 0): List<T> {
    return this.filterIndexed { index, _ -> index % step == offset }
}

fun List<Int>.multiply(): Int = reduce { acc, number -> acc * number }
