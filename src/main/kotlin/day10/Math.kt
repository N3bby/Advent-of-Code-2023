package day10

data class Position(val x: Int, val y: Int) {
    operator fun plus(offset: Offset): Position {
        return Position(x + offset.x, y + offset.y)
    }

    operator fun minus(position: Position): Offset {
        return Offset(x - position.x, y - position.y)
    }

    val neighbours: List<Position>
        get() {
            return listOf(
                this + Offset(1, 0),
                this + Offset(-1, 0),
                this + Offset(0, 1),
                this + Offset(0, -1),
            )
        }
}

data class Offset(val x: Int, val y: Int)