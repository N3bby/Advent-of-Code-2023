package day11

import day11.ExpandedSpaceDimension.HORIZONTAL
import day11.ExpandedSpaceDimension.VERTICAL
import ext.getAllUniqueCombinations
import util.Line
import util.Position

data class Universe(private val galaxies: List<Galaxy>, private val ageOfUniverse: Int = 1) {

    val sumOfDistancesBetweenGalaxies: Long
        get() = galaxies
            .getAllUniqueCombinations()
            .map { (galaxy1, galaxy2) -> Line(galaxy1.position, galaxy2.position) }
            .sumOf { line ->
                val expandedSpacesCrossed = expandedSpaces.count { it.isCrossedBy(line) }
                line.manhattanDistance.toLong() - expandedSpacesCrossed + expandedSpacesCrossed * ageOfUniverse
            }

    private val expandedSpaces: List<ExpandedSpace> by lazy {
        emptyRowPositions.map { ExpandedSpace(it, HORIZONTAL) } +
        emptyColumnPositions.map { ExpandedSpace(it, VERTICAL) }
    }

    private val emptyRowPositions: List<Int> by lazy {
        (minY..maxY).filter { y -> galaxies.none { it.position.y == y } }
    }

    private val emptyColumnPositions: List<Int> by lazy {
        (minX..maxX).filter { x -> galaxies.none { it.position.x == x } }
    }

    private val minX = galaxies.minBy { it.position.x }.position.x
    private val maxX = galaxies.maxBy { it.position.x }.position.x
    private val minY = galaxies.minBy { it.position.y }.position.y
    private val maxY = galaxies.maxBy { it.position.y }.position.y

    fun atAge(age: Int): Universe {
        return Universe(galaxies, age)
    }

}

data class Galaxy(val position: Position) : Comparable<Galaxy> {
    override fun compareTo(other: Galaxy): Int {
        return position.compareTo(other.position)
    }
}

data class ExpandedSpace(private val position: Int, private val dimension: ExpandedSpaceDimension) {
    fun isCrossedBy(line: Line): Boolean {
        return when (dimension) {
            HORIZONTAL -> line.crossesY(position)
            VERTICAL -> line.crossesX(position)
        }
    }
}

enum class ExpandedSpaceDimension {
    HORIZONTAL,
    VERTICAL
}
