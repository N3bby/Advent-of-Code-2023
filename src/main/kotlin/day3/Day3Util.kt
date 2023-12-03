package day3

data class Bounds(val x: IntRange, val y: IntRange) {
    fun expand(size: Int): Bounds {
        return Bounds(
            x.first - size..x.last + size,
            y.first - size..y.last + size,
        )
    }

    fun contains(position: Position): Boolean {
        return position.x in x && position.y in y
    }
}

data class Position(val x: Int, val y: Int)
