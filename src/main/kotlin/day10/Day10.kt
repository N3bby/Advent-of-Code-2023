package day10

import day10.Tile.Pipe.Companion
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import kotlin.math.floor

data class Position(val x: Int, val y: Int) {
    operator fun plus(offset: Offset): Position {
        return Position(x + offset.x, y + offset.y)
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

sealed class Tile(
    val position: Position,
) {
    sealed class Pipe(
        position: Position,
        private val connection1: Position,
        private val connection2: Position
    ) : Tile(position) {
        class VerticalPipe(position: Position) : Pipe(
            position = position,
            connection1 = position + Offset(0, 1),
            connection2 = position + Offset(0, -1)
        )

        class HorizontalPipe(position: Position) : Pipe(
            position = position,
            connection1 = position + Offset(1, 0),
            connection2 = position + Offset(-1, 0)
        )

        class BottomLeftCornerPipe(position: Position) : Pipe(
            position = position,
            connection1 = position + Offset(0, 1),
            connection2 = position + Offset(1, 0)
        )

        class BottomRightCornerPipe(position: Position) : Pipe(
            position = position,
            connection1 = position + Offset(-1, 0),
            connection2 = position + Offset(0, 1)
        )

        class UpperRightCornerPipe(position: Position) : Pipe(
            position = position,
            connection1 = position + Offset(0, -1),
            connection2 = position + Offset(-1, 0)
        )

        class UpperLeftCornerPipe(position: Position) : Pipe(
            position = position,
            connection1 = position + Offset(1, 0),
            connection2 = position + Offset(0, -1)
        )

        fun connectsWith(tile: Tile): Boolean {
            return connection1 == tile.position || connection2 == tile.position
        }

        fun nextPosition(cameFrom: Position): Position {
            return if (cameFrom == connection1) connection2 else connection1
        }

        companion object {
            tailrec fun follow(currentPipe: Pipe, previousTile: Tile, path: List<Tile>, sketch: Sketch): Loop? {
                val nextTile = sketch.getTile(currentPipe.nextPosition(cameFrom = previousTile.position))
                return when (nextTile) {
                    in path -> Loop(path + currentPipe)
                    !is Pipe -> return null
                    else -> follow(nextTile, currentPipe, path + currentPipe, sketch)
                }
            }
        }
    }

    class Ground(position: Position) : Tile(position)
    class StartingPosition(position: Position) : Tile(position)

    companion object {
        fun fromSymbol(position: Position, symbol: Char): Tile {
            return when (symbol) {
                '.' -> Ground(position)
                'S' -> StartingPosition(position)
                '|' -> Pipe.VerticalPipe(position)
                '-' -> Pipe.HorizontalPipe(position)
                'L' -> Pipe.BottomLeftCornerPipe(position)
                'J' -> Pipe.BottomRightCornerPipe(position)
                '7' -> Pipe.UpperRightCornerPipe(position)
                'F' -> Pipe.UpperLeftCornerPipe(position)
                else -> throw IllegalArgumentException("No Tile definition exists for symbol '$symbol'")
            }
        }
    }
}

data class Loop(private val tiles: List<Tile>) {
    val furthestDistanceFromStart: Int
        get() {
            return floor(tiles.size / 2.0).toInt()
        }
}

data class Sketch(private val tiles: Map<Position, Tile>) {

    fun getTile(position: Position): Tile? {
        return tiles[position]
    }

    fun findLoop(): Loop {
        val startingPosition = tiles.values.find { it is Tile.StartingPosition }
            ?: throw IllegalStateException("No starting position could be found")

        val potentialLoops = startingPosition.position.neighbours
            .mapNotNull { getTile(it) }
            .filterIsInstance<Tile.Pipe>()
            .filter { it.connectsWith(startingPosition) }

        return potentialLoops
            .mapNotNull { Tile.Pipe.follow(
                currentPipe = it,
                previousTile = startingPosition,
                path = listOf(startingPosition),
                sketch = this
            ) }
            .firstOrNull()
            ?: throw IllegalStateException("No loop could be found")
    }
}

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