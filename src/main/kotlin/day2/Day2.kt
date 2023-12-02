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
        val minimumRequiredBag = mutableMapOf<Color, Int>()
        sets.forEach { set ->
            set.cubes.forEach { setItem ->
                val color = setItem.key
                val amount = setItem.value

                if (color !in minimumRequiredBag || amount > minimumRequiredBag[color]!!) {
                    minimumRequiredBag[color] = amount
                }
            }
        }
        return Bag(minimumRequiredBag)
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
