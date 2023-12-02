package day2

fun parseGames(input: List<String>): List<Game> {
    return input.map(::parseGame)
}

/**
 * Receives (e.g.) "Game 1: 3 red; 2 green, 1 blue; 9 green"
 */
fun parseGame(line: String): Game {
    val tokens = line.split(':')

    val id = tokens[0].split(' ')[1].toInt()
    val sets = tokens[1].split(';').map(::parseGameSet)

    return Game(id, sets)
}

/**
 * Receives (e.g.) "5 red, 6 green, 2 blue"
 */
fun parseGameSet(set: String): GameSet {
    return GameSet(
        set.split(',')
            .map(String::trim)
            .associate(::parseGameSetItem)
    )
}

/**
 * Receives (e.g.) "5 red"
 */
fun parseGameSetItem(setItem: String): Pair<Color, Int> {
    val tokens = setItem.split(' ')

    val amount = tokens[0].toInt()
    val color = tokens[1]

    return color to amount
}

