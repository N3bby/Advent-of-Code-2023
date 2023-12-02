package day2

typealias Color = String

data class Bag(val cubes: Map<Color, Int>)

data class Game(val id: Int, val sets: List<GameSet>) {
    fun isPossibleWith(bag: Bag): Boolean {
        return sets.all { it.isPossibleWith(bag) }
    }

    fun getGamePower(): Int {
        return getMinimumRequiredBag().cubes.values
            .reduce { acc, value -> acc * value }
    }

    private fun getMinimumRequiredBag(): Bag {
        val colors = sets.flatMap { it.cubes.keys }.toSet()
        val minimumRequiredCubes = colors.associateWith { color ->
            sets
                .map { it.cubes[color] ?: 0 }
                .max()
        }
        return Bag(minimumRequiredCubes)
    }
}

data class GameSet(val cubes: Map<Color, Int>) {
    fun isPossibleWith(bag: Bag): Boolean {
        return cubes.all {
            val color = it.key
            val amount = it.value

            color in bag.cubes && amount <= bag.cubes[color]!!
        }
    }
}

fun getPossibleGames(games: List<Game>, bag: Bag): List<Game> {
    return games.filter { it.isPossibleWith(bag) }
}
