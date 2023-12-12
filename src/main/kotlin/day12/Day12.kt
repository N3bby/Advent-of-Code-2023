package day12

enum class SpringCondition {
    SPRING,
    EMPTY,
    UNKNOWN,
}

data class SpringRecord(
    private val conditions: List<SpringCondition>,
    private val groupSizes: List<Int>
)

data class SpringRecordCollection(private val springRecord: List<SpringRecord>) {
    val sumOfPossibleArrangements: Int get() = TODO()
}

fun parseSpringRecords(input: String): SpringRecordCollection {
    TODO()
}
