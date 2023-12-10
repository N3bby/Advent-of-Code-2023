package day10

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
        ) {
            override fun getTilesOnInside(cameFrom: Position): List<Position> {
                val cameFromBottom = (position - cameFrom).y >= 1
                return if (cameFromBottom) listOf(position + Offset(1, 0))
                else listOf(position + Offset(-1, 0))
            }

            override fun isRightTurn(cameFrom: Position): Boolean? {
                return null
            }
        }

        class HorizontalPipe(position: Position) : Pipe(
            position = position,
            connection1 = position + Offset(1, 0),
            connection2 = position + Offset(-1, 0)
        ) {
            override fun getTilesOnInside(cameFrom: Position): List<Position> {
                val cameFromLeft = (position - cameFrom).x >= 1
                return if (cameFromLeft) listOf(position + Offset(0, -1))
                else listOf(position + Offset(0, 1))
            }

            override fun isRightTurn(cameFrom: Position): Boolean? {
                return null
            }
        }

        class BottomLeftCornerPipe(position: Position) : Pipe(
            position = position,
            connection1 = position + Offset(0, 1),
            connection2 = position + Offset(1, 0)
        ) {
            override fun getTilesOnInside(cameFrom: Position): List<Position> {
                return if (isRightTurn(cameFrom)) listOf(position + Offset(1, 1))
                else listOf(
                    position + Offset(-1, 0),
                    position + Offset(0, -1),
                    position + Offset(-1, -1)
                )
            }

            override fun isRightTurn(cameFrom: Position): Boolean {
                return (position - cameFrom).x <= -1
            }
        }

        class BottomRightCornerPipe(position: Position) : Pipe(
            position = position,
            connection1 = position + Offset(-1, 0),
            connection2 = position + Offset(0, 1)
        ) {
            override fun getTilesOnInside(cameFrom: Position): List<Position> {
                return if (isRightTurn(cameFrom)) listOf(position + Offset(-1, 1))
                else listOf(
                    position + Offset(1, 0),
                    position + Offset(0, -1),
                    position + Offset(1, -1)
                )
            }

            override fun isRightTurn(cameFrom: Position): Boolean {
                return (position - cameFrom).y <= -1
            }
        }

        class UpperRightCornerPipe(position: Position) : Pipe(
            position = position,
            connection1 = position + Offset(0, -1),
            connection2 = position + Offset(-1, 0)
        ) {
            override fun getTilesOnInside(cameFrom: Position): List<Position> {
                return if (isRightTurn(cameFrom)) listOf(position + Offset(-1, -1))
                else listOf(
                    position + Offset(1, 0),
                    position + Offset(0, 1),
                    position + Offset(1, 1)
                )
            }

            override fun isRightTurn(cameFrom: Position): Boolean {
                return (position - cameFrom).x >= 1
            }
        }

        class UpperLeftCornerPipe(position: Position) : Pipe(
            position = position,
            connection1 = position + Offset(1, 0),
            connection2 = position + Offset(0, -1)
        ) {
            override fun getTilesOnInside(cameFrom: Position): List<Position> {
                return if (isRightTurn(cameFrom)) listOf(position + Offset(1, -1))
                else listOf(
                    position + Offset(-1, 0),
                    position + Offset(0, 1),
                    position + Offset(-1, 1)
                )
            }

            override fun isRightTurn(cameFrom: Position): Boolean {
                return (position - cameFrom).y >= 1
            }
        }

        fun connectsWith(tile: Tile): Boolean {
            return connection1 == tile.position || connection2 == tile.position
        }

        fun nextPosition(cameFrom: Position): Position {
            return if (cameFrom == connection1) connection2 else connection1
        }

        /**
         * This assumes a loop that goes in clockwise direction
         */
        abstract fun getTilesOnInside(cameFrom: Position): List<Position>

        /**
         * This assumes a loop that goes in clockwise direction
         */
        abstract fun isRightTurn(cameFrom: Position): Boolean?

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

