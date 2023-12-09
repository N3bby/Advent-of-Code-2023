package day9

data class OasisValueHistory(private val history: List<Int>) {
    fun extrapolateHistoryToFuture(): Int {
        return extrapolateHistory(
            history,
            valueToExtrapolate = { it.last },
            extrapolationOperation = Int::plus
        )
    }

    fun extrapolateHistoryToPast(): Int {
        return extrapolateHistory(
            history,
            valueToExtrapolate = { it.first },
            extrapolationOperation = Int::minus
        )
    }

    private fun extrapolateHistory(
        values: List<Int>,
        valueToExtrapolate: (values: List<Int>) -> Int,
        extrapolationOperation: (value: Int, extrapolation: Int) -> Int,
    ): Int {
        return if (values.all { it == 0 }) {
            0
        } else {
            val differences = values
                .windowed(2, 1)
                .map { (value1, value2) -> value2 - value1 }
            extrapolationOperation(
                valueToExtrapolate(values),
                extrapolateHistory(differences, valueToExtrapolate, extrapolationOperation)
            )
        }
    }
}

data class OasisReport(private val histories: List<OasisValueHistory>) {
    val sumOfFutureExtrapolatedHistories: Int
        get() = histories
            .sumOf { it.extrapolateHistoryToFuture() }

    val sumOfPastExtrapolatedHistories: Int
        get() = histories
            .sumOf { it.extrapolateHistoryToPast() }
}

fun parseOasisReport(input: String): OasisReport {
    val histories = input.lines()
        .map { line ->
            line
                .split(" ")
                .map { it.toInt() }
        }
        .map { OasisValueHistory(it) }

    return OasisReport(histories)
}
