package day10

fun parseSketch(input: String): Sketch {
    val tiles = input
        .lines()
        .reversed() // Reversed so y + 1 is up and y - 1 is down
        .flatMapIndexed { index, line -> parseLine(index, line) }
        .associateBy { it.position }

    return Sketch(tiles)
}

fun parseLine(y: Int, line: String): List<Tile> {
    return line.mapIndexed { x, symbol -> Tile.fromSymbol(Position(x, y), symbol) }
}