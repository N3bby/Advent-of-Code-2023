package day10

import ext.rotateRight
import util.Position
import java.util.*
import kotlin.math.floor

data class Loop(private val tiles: List<Tile>) {

    val loopPositions = tiles.map { it.position }

    val furthestDistanceFromStart: Int get() = floor(tiles.size / 2.0).toInt()

    private val clockwise: Boolean
        get() {
            val rightTurns = tiles
                .windowed(2, 1)
                .map { (tile1, tile2) -> (tile2 as Tile.Pipe).isRightTurn(tile1.position) }
            return rightTurns.count { it == true } > rightTurns.count { it == false }
        }

    private fun asClockwise(): Loop {
        return if(!clockwise) {
            Loop(tiles.reversed().rotateRight(1))
        } else {
            this
        }
    }

    fun findAmountOfTilesInsideLoop(): Int {
        val loop = asClockwise()

        val floodFillSeeds = loop.tiles
            .windowed(2, 1)
            .flatMap { (tile1, tile2) -> (tile2 as Tile.Pipe).getTilesOnInside(tile1.position) }
            .filter { it !in loopPositions }
            .toSet()

        // Do a floodfill
        val visitedTiles = floodFillSeeds.toMutableList()
        val tilesToFloodFrom: Queue<Position> = LinkedList(floodFillSeeds)
        while (tilesToFloodFrom.peek() != null) {
            val tile = tilesToFloodFrom.poll()
            val newlyVisitedTiles = tile.neighbours
                .filter { it !in visitedTiles }
                .filter { it !in loopPositions }
            visitedTiles.addAll(newlyVisitedTiles)
            tilesToFloodFrom.addAll(newlyVisitedTiles)
        }

        return visitedTiles.size
    }


}
