package day2

typealias Color = String

data class Bag(val cubes: Map<Color, Int>)

data class Game(val id: Int, val sets: List<GameSet>) {
    fun isPossibleWith(bag: Bag): Boolean {
        return sets.all { it.isPossibleWith(bag) }
    }

    fun getPower(): Int {
        return getMinimumRequiredBag().cubes.values
            .reduce { acc, value -> acc * value }
    }

    private fun getMinimumRequiredBag(): Bag {
        val minimumRequiredBag = mutableMapOf<Color, Int>()
        sets.forEach { set ->
            set.cubes.forEach { setItem ->
                if(setItem.key !in minimumRequiredBag || setItem.value > minimumRequiredBag[setItem.key]!!) {
                    minimumRequiredBag[setItem.key] = setItem.value
                }
            }
        }
        return Bag(minimumRequiredBag)
    }
}

data class GameSet(val cubes: Map<Color, Int>) {
    fun isPossibleWith(bag: Bag): Boolean {
        return cubes.all {
            it.key in bag.cubes && it.value <= bag.cubes[it.key]!!
        }
    }
}

fun getPossibleGames(games: List<Game>, bag: Bag): List<Game> {
    return games.filter { it.isPossibleWith(bag) }
}

fun parseGames(input: List<String>): List<Game> {
    return input.map(::parseGame)
}

fun parseGame(line: String): Game {
    val id = line.split(':')[0].split(' ')[1].toInt()
    val sets = line.split(':')[1].split(';')
        .map { set ->
            set.split(',')
                .map { it.trim() }
                .associate { setItem ->
                    val amount = setItem.split(' ')[0].toInt()
                    val color = setItem.split(' ')[1]
                    Pair(color, amount)
                }
        }
        .map { GameSet(it) }
    return Game(id, sets)
}
