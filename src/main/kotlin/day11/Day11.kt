package day11

import day11.ExpandedSpaceDimension.HORIZONTAL
import day11.ExpandedSpaceDimension.VERTICAL
import ext.getAllUniqueCombinations
import util.Line
import util.Position

data class Universe(private val galaxies: List<Galaxy>, private val age: Int = 1) {

    val sumOfDistancesBetweenGalaxies: Long
        get() = galaxies
            .getAllUniqueCombinations()
            .map { (galaxy1, galaxy2) -> Line(galaxy1.position, galaxy2.position) }
            .sumOf { line ->
                val expandedUnitsCrossed = expandedSpace.count { it.isCrossedBy(line) }
                line.manhattanDistance.toLong() - expandedUnitsCrossed + expandedUnitsCrossed * age
            }

    private val expandedSpace: List<ExpandedSpace> by lazy {
        emptyRowLocations.map { ExpandedSpace(it, HORIZONTAL) } +
        emptyColumnLocations.map { ExpandedSpace(it, VERTICAL) }
    }

    private val emptyRowLocations: List<Int> by lazy {
        (minY..maxY).filter { y -> galaxies.none { it.position.y == y } }
    }

    private val emptyColumnLocations: List<Int> by lazy {
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
