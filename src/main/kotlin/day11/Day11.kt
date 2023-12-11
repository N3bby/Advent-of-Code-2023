package day11

import day11.UniverseElement.Empty
import day11.UniverseElement.Galaxy
import ext.getAllUniqueCombinations
import util.Position

/**
 * Grid with origin defined in the top left corner
 */
data class Grid<T>(val rows: List<List<T>>) {

    val width = rows[0].size
    val height = rows.size

    val columns: List<List<T>>
        get() {
            return (0..<width).map { column -> rows.map { it[column] } }
        }

    fun getAtPosition(position: Position): T {
        return rows[position.y][position.x]
    }

    fun getPositionsWhere(predicate: (cell: T) -> Boolean): List<Position> {
        val matchingCells = mutableListOf<Position>()

        for (x in 0..<width) {
            for (y in 0..<height) {
                val position = Position(x, y)
                if (predicate(getAtPosition(position))) matchingCells.add(position)
            }
        }

        return matchingCells
    }

    fun insertRow(index: Int, row: List<T>): Grid<T> {
        if (row.size != width) throw IllegalArgumentException("Inserted row (width ${row.size}) must be the same width as the grid (width $width)")
        return Grid(rows.take(index) + listOf(row) + rows.drop(index))
    }

    fun insertColumn(index: Int, column: List<T>): Grid<T> {
        if (column.size != height) throw IllegalArgumentException("Inserted column (height ${column.size}) must be the same height as the grid (height $height)")
        return Grid(
            rows.mapIndexed { rowIndex, row ->
                row.take(index) + listOf(column[rowIndex]) + row.drop(index)
            }
        )
    }
}

sealed class UniverseElement {

    data object Empty : UniverseElement()
    class Galaxy(val id: Int) : UniverseElement()

}

data class Universe(private val grid: Grid<UniverseElement>) {

    private val galaxies: List<Position> get() = grid.getPositionsWhere { it is Galaxy }

    val sumOfDistancesBetweenGalaxies: Int
        get() = galaxies
            .getAllUniqueCombinations()
            .sumOf { (galaxy1, galaxy2) -> galaxy1.manhattanDistanceFrom(galaxy2) }

    fun expand(): Universe {
        val verticallyExpandedGrid = expandDimension(
            grid,
            dimensionToExpand = { grid -> grid.rows },
            expandOperation = { grid, index, dimensionSlice -> grid.insertRow(index, dimensionSlice) }
        )
        val expandedGrid = expandDimension(
            verticallyExpandedGrid,
            dimensionToExpand = { grid -> grid.columns },
            expandOperation = { grid, index, dimensionSlice -> grid.insertColumn(index, dimensionSlice) }
        )
        return Universe(expandedGrid)
    }

}

private fun expandDimension(
    grid: Grid<UniverseElement>,
    dimensionToExpand: (grid: Grid<UniverseElement>) -> List<List<UniverseElement>>,
    expandOperation: (grid: Grid<UniverseElement>, index: Int, dimensionSlice: List<UniverseElement>) -> Grid<UniverseElement>,
): Grid<UniverseElement> {
    var expandedSlices = 0
    return dimensionToExpand(grid).foldIndexed(grid) { index, expandingGrid, dimensionSlice ->
        if (dimensionSlice.all { it is Empty }) {
            val expandedGrid = expandOperation(expandingGrid, index + expandedSlices, dimensionSlice)
            expandedSlices++
            expandedGrid
        } else {
            expandingGrid
        }
    }
}

fun parseUniverse(input: String): Universe {
    var galaxyCounter = 0
    val grid = Grid(input.lines()
        .map { line ->
            line.toCharArray().map { symbol ->
                if (symbol == '#') {
                    Galaxy(++galaxyCounter)
                } else if (symbol == '.') {
                    Empty
                } else {
                    throw IllegalArgumentException("Cannot parse symbol '$symbol' to UniverseElement")
                }
            }
        })
    return Universe(grid)
}
