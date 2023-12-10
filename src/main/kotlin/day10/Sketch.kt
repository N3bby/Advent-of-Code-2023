package day10

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
            .mapNotNull {
                Tile.Pipe.follow(
                    currentPipe = it,
                    previousTile = startingPosition,
                    path = listOf(startingPosition),
                    sketch = this
                )
            }
            .firstOrNull()
            ?: throw IllegalStateException("No loop could be found")
    }
}