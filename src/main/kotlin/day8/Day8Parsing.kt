package day8

fun parseNetwork(input: String): Network {
    val lines = input.lines()

    val instructions = parseInstructions(lines.first())
    val nodes = parseNodes(lines.drop(2))

    return Network(nodes, instructions)
}

/**
 * Receives (e.g.) "22B = (22C, 22C)"
 */
fun parseNodes(lines: List<String>): List<Node> {
    val regex = """(.+) = \((.+), (.+)\)""".toRegex()
    return lines.map { line ->
        val matchResult = regex.matchEntire(line)
            ?: throw IllegalArgumentException("Could not parse node line '$line'")

        Node(
            matchResult.groupValues[1],
            matchResult.groupValues[2],
            matchResult.groupValues[3]
        )
    }
}

/**
 * Receives (e.g.) "LLR"
 */
fun parseInstructions(line: String): List<Instruction> {
    return line.toCharArray().map {
        when (it) {
            'L' -> Instruction.LEFT
            'R' -> Instruction.RIGHT
            else -> throw IllegalArgumentException("Unknown instruction '$it'")
        }
    }
}