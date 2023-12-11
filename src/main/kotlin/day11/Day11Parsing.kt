package day11

import util.Position

fun parseUniverse(input: String): Universe {
    return Universe(galaxies = input.lines().flatMapIndexed(::parseLine))
}

fun parseLine(lineIndex: Int, line: String): List<Galaxy> {
    return line.toCharArray().mapIndexed { index, symbol ->
        if (symbol == '#') {
            Galaxy(Position(index, lineIndex))
        } else {
            null
        }
    }.filterNotNull()
}
