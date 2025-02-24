package com.afedare

import com.afedare.Positions.CAM
import com.afedare.Positions.CB
import com.afedare.Positions.CDM
import com.afedare.Positions.CM
import com.afedare.Positions.GK
import com.afedare.Positions.LB
import com.afedare.Positions.LM
import com.afedare.Positions.LW
import com.afedare.Positions.RB
import com.afedare.Positions.RM
import com.afedare.Positions.RW
import com.afedare.Positions.ST
import kotlin.math.min
import kotlin.random.Random

fun maxRatingPlayer(players: List<Player>, ratingLimit: Int): Double {
    val ratingSize = players.maxOf { it.rating }
    return maxXCount(ratingSize, ratingLimit.toDouble())
}

fun minRatingPlayer(players: List<Player>, ratingLimit: Int): Double {
    val ratingSize = players.minOf { it.rating }
    return minXCount(ratingSize, ratingLimit.toDouble())
}

fun minXSamenation(players: List<Player>, nationLimit: Double): Double {
    val maxSameNation = players.groupBy { p -> p.nation }.map { it.value.count() }.max()
    return minXCount(maxSameNation, nationLimit)
}

fun minXSameLeague(players: List<Player>, nationLimit: Double): Double {
    val maxLeague = players.groupBy { p -> p.league }.map { it.value.count() }.max()
    return minXCount(maxLeague, nationLimit)
}

fun maxXSameLeague(players: List<Player>, limit: Double): Double {
    val maxLeague = players.groupBy { p -> p.league }.map { it.value.count() }.max()
    return maxXCount(maxLeague, limit)
}


fun minXSameclub(players: List<Player>, nationLimit: Double): Double {
    val maxClub = players.groupBy { p -> p.club }.map { it.value.count() }.max()
    return minXCount(maxClub, nationLimit)
}

fun maxXSameClub(players: List<Player>, nationLimit: Double): Double {
    val maxClub = players.groupBy { p -> p.club }.map { it.value.count() }.max()
    return maxXCount(maxClub, nationLimit)
}

fun maxXClubs(players: List<Player>, limit: Double): Double {
    val clubs = players.map { it.club }.toSet()
    val size = clubs.size
    return maxXCount(size, limit)
}

fun minChem(formation: List<String>, players: List<Player>, chemLimit: Int): Double {
    val chem = chemistry(formation, players)
    return minXCount(chem, chemLimit.toDouble())
}

fun minXClubs(players: List<Player>, limit: Double): Double {
    val clubs = players.map { it.club }.toSet()
    val size = clubs.size
    return minXCount(size, limit)
}

fun minXNation(players: List<Player>, nationLimit: Double): Double {
    val nations = players.map { it.nation }.toSet()
    val nationSize = nations.size
    return minXCount(nationSize, nationLimit)
}

fun maxLeague(players: List<Player>, limit: Double): Double {
    val count = players.map { it.league }.toSet().size
    return maxXCount(count, limit)
}

fun minLeague(players: List<Player>, limit: Double): Double {
    val count = players.map { it.league }.toSet().size
    return minXCount(count, limit)
}

fun minXFromNation(players: List<Player>, nation: String, nationLimit: Double): Double {
    val nationSize = players.count { it.nation == nation }
    return minXCount(nationSize, nationLimit)
}

fun minXFromLeague(players: List<Player>, league: String, limit: Double): Double {
    val leagueCount = players.count { it.league == league }
    return minXCount(leagueCount, limit)
}

fun maxXFromLeague(players: List<Player>, league: String, limit: Double): Double {
    val leagueCount = players.count { it.league == league }
    return maxXCount(leagueCount, limit)
}

fun minXFromClub(players: List<Player>, club: String, limit: Double): Double {
    val size = players.count { it.club == club }
    return minXCount(size, limit)
}

fun maxNation(players: List<Player>, nationMax: Double): Double {
    val nations = players.map { it.nation }.toSet()
    val nationSize = nations.size
    return maxXCount(nationSize, nationMax)
}

fun minXRare(players: List<Player>, limit: Double): Double {
    val count = players.count { it.rarity.contains("Rare") }
    return minXCount(count, limit)
}

private fun minXGold(players: List<Player>, limit: Double): Double {
    val count = players.count { it.rarity.contains("Gold") }
    return minXCount(count, limit)
}

fun minRating(players: List<Player>, limit: Double): Double {
    val count = (players.sbcAverage()).toInt()
    return minXCount(count, limit)
}

fun maxRating(players: List<Player>, limit: Double): Double {
    val count = (players.sbcAverage()).toInt()
    return maxXCount(count, limit)
}

fun List<Player>.sbcAverage(): Double {
    val average = sumOf { it.rating } / size.toDouble()
    val averageInt = average.toInt()
    val boostPoints = sumOf {
        val diff = it.rating - averageInt
        if (diff > 0) diff else 0
    } / size.toDouble()
    return average + boostPoints
}

private fun minXCount(count: Int, limit: Double): Double {
    return (count / limit).coerceAtMost(1.0)
}

private fun maxXCount(count: Int, limit: Double): Double {
    return if (count <= limit) {
        1.0
    } else {
        limit / count
    }
}


fun main() {
    /* Best squad, basic chem
    val result = geneticAlgo(
        formation = _4213, allPlayers = readClub(), restrictions = listOf(
            min33Chem, maxRatingPlayer
        )
    )*/

    /*
    grassroot great 2
    val result = geneticAlgo(
        formation = _433, allPlayers = readClub(), restrictions = listOf(
            max5Leagues, min5Clubs, min5Rare, min31Chem, min80Rating, max83RatingPlayer
        )
    )*/


    //  grassroot great 3
    /* val result = geneticAlgo(
         formation = _4222, allPlayers = readClub(), restrictions = listOf(
             min5SameNation, max5Leagues, min5Clubs, min5Rare, min80Rating, min31Chem, max83RatingPlayer
         )
     )*/

    // ligue1
    /* val result = geneticAlgo(
         formation = _442, allPlayers = readClub(), restrictions = listOf(
             min6SameClub, minLique11, min2Rare, min68Rating, min10Chem, max83RatingPlayer
         )
     )*/

    // bundesliga
    /*val result = geneticAlgo(
        formation = _4222, allPlayers = readClub(), restrictions = listOf(
            minSerieA11, max1Clubs, min3Rare, min77Rating, min10Chem, max83RatingPlayer
        )
    )*/

    /* val result = geneticAlgo(
         formation = _4231, allPlayers = readClub(), restrictions = listOf(
             minPrem11, max1Clubs, min4Rare, min79Rating, min10Chem, max83RatingPlayer
         )
     )

     println("\nBest SBC Squad Found:")
     println("\nChemistry: ${chemistry(_433, result)}")
     result.forEach { player: Player ->
         val name = player.name()
         println("$name /${player.club} /${player.nation} /${player.rating}")
     }
     println("\nSquad Rating: ${result.sumOf { it.rating } / result.size}")
     val page = launchEafc()
     page.apply {
         goToSbc()
         sbcTitle("Premium Mixed Leagues Upgrade")
         selectSubSbc(2)
         workTab()
         for (i in result.indices) {
             val player = result[i]
             val subsLocator = selectSub(i)
             subsLocator.click()
             locator("div.DetailPanel button span.btn-text:has-text('Add Player')").click()
             highLowToLowHigh()
             selectConceptPlayers()
             searchPlayer(player.name())
         }
     }*/
}

const val POPULATION_SIZE = 500
const val GENERATIONS = 2000

fun geneticAlgo(
    populationSize: Int = POPULATION_SIZE,
    generations: Int = GENERATIONS,
    formation: List<String>,
    allPlayers: List<Player>,
    restrictions: List<IRestrict>
): List<Player> {
    var population = List(populationSize) { generateSquad(formation, allPlayers) }
    repeat(generations) {
        val selected = selection(population, formation, restrictions, populationSize)
        val nextGeneration = mutableListOf<List<Player>>()
        while (nextGeneration.size < populationSize) {
            val (parent1, parent2) = selected.shuffled().take(2)
            var child = crossover(parent1, parent2)
            child = mutate(child, allPlayers)
            nextGeneration.add(child)
        }
        population = nextGeneration
    }
    val bestSquad = selection(population, formation, restrictions, populationSize).first()
    return bestSquad
}

fun interface IRestrict {
    fun score(formation: List<String>, players: List<Player>): Double
}

fun generateSquad(positions: List<String>, allPlayers: List<Player>): List<Player> {
    val len = positions.size
    return allPlayers.shuffled().take(len)
}

fun fitness(formation: List<String>, players: List<Player>, restriction: List<IRestrict>): Double {
    val score = restriction.sumOf {
        val result = it.score(formation, players)
        result
    }
    val distinctSize = players.map { "${it.fullName} ${it.knownAs} ${it.club}" }.toSet().size
    if (distinctSize != players.size) {
        return 0.0
    }
    return score
}

fun crossover(playersA: List<Player>, playersB: List<Player>): List<Player> {
    val midSize = (min(playersA.size, playersB.size) / 2) - 1
    return playersA.take(midSize) + playersB.drop(midSize)
}

fun selection(
    population: List<List<Player>>,
    formation: List<String>,
    restriction: List<IRestrict>,
    populationSize: Int
): List<List<Player>> {
    val topPopulace = population.sortedByDescending { players ->
        fitness(formation, players, restriction) + (1 - (players.sbcAverage() / 100.0))
    }.take(populationSize / 2)
    println("Chem ${fitness(formation, topPopulace.first(), restriction)}")
    return topPopulace
}

fun mutate(players: List<Player>, allPlayers: List<Player>): List<Player> {
    val mutationRate = 0.6
    val playerMutable = players.toMutableList()

    if (Random.nextDouble() < mutationRate) {
        val swapTypes = listOf("randomSwap", "replaceLeague", "replaceClub")
        val swapType = swapTypes.random()
        when (swapType) {
            "randomSwap" -> {
                val index = Random.nextInt(0, players.size)
                playerMutable[index] = allPlayers.random()
            }

            "replaceLeague" -> {
                val index = Random.nextInt(0, players.size)
                val index1 = Random.nextInt(0, players.size)
                val playerLeague = playerMutable[index].league
                val filter = allPlayers.filter { player: Player ->
                    player.league == playerLeague && player.hasPosition(playerMutable[index1].preferredPosition)
                }
                if (filter.isNotEmpty()) {
                    playerMutable[index1] = filter.random()
                }
            }

            "replaceClub" -> {
                val index = Random.nextInt(0, players.size)
                val index1 = Random.nextInt(0, players.size)
                val playerClub = playerMutable[index].club
                val filter =
                    allPlayers.filter { player: Player -> player.club == playerClub && player.hasPosition(playerMutable[index1].preferredPosition) }
                if (filter.isNotEmpty()) {
                    playerMutable[index1] = filter.random()
                }
            }
        }
//        val index = Random.nextInt(0, players.size)
//        playerMutable[index] = allPlayers.random()
    }
    return playerMutable
}

fun chemistry(positions: List<String>, players: List<Player>): Int {
    var totalChem = 0
    val positionPlayers = playersToPosition(players, positions)

    for ((position, player) in positionPlayers) {
        var leagueCount = 1
        var clubCount = 1
        var nationCount = 1

        if (position != null) {
            for ((positionB, playerB) in positionPlayers) {
                if (player == playerB || positionB == null) continue
                if (player.league == playerB.league) leagueCount++
                if (player.club == playerB.club) clubCount++
                if (player.nation == playerB.nation) nationCount++
            }
        }

        // Club chem
        var playerChem = 0
        if (clubCount >= 7) {
            playerChem += 3
        } else if (clubCount >= 4) {
            playerChem += 2
        } else if (clubCount >= 2) {
            playerChem += 1
        }

        // Nation chem
        if (nationCount >= 8) {
            playerChem += 3
        } else if (nationCount >= 5) {
            playerChem += 2
        } else if (nationCount >= 2) {
            playerChem += 1
        }

        // League chem
        if (leagueCount >= 8) {
            playerChem += 3
        } else if (leagueCount >= 5) {
            playerChem += 2
        } else if (leagueCount >= 3) {
            playerChem += 1
        }

        playerChem = min(3, playerChem)
        totalChem += playerChem
    }
    return totalChem
}

fun playersToPosition(
    players: List<Player>,
    positions: List<String>
): List<Pair<String?, Player>> {
    val positionLeft = positions.toMutableList()
    val positionPlayers = mutableListOf<Pair<String?, Player>>()
    for (player in players) {
        if (player.preferredPosition in positionLeft) {
            positionLeft.remove(player.preferredPosition)
            positionPlayers.add(Pair(player.preferredPosition, player))
        } else {
            val alternatePositions = player.alternatePositions.split(",").map { it.trim() }
            if (alternatePositions.isEmpty()) {
                positionPlayers.add(Pair(null, player))
            } else {
                val index = positionLeft.indexOfFirst { position -> alternatePositions.contains(position) }
                if (index == -1)
                    positionPlayers.add(Pair(null, player))
                else {
                    positionPlayers.add(Pair(positionLeft[index], player))
                    positionLeft.removeAt(index)
                }
            }
        }
    }
    return positionPlayers
}

object Positions {
    const val ST = "ST"
    const val LW = "LW"
    const val LM = "LM"
    const val RW = "RW"
    const val RM = "RM"
    const val CM = "CM"
    const val CDM = "CDM"
    const val CAM = "CAM"
    const val LB = "LB"
    const val CB = "CB"
    const val RB = "RB"
    const val GK = "GK"
}

object Formation {
    val _433 = listOf(LW, ST, RW, CM, CM, CM, LB, CB, CB, RB, GK)
    val _4213 = listOf(LW, ST, RW, CAM, CDM, CDM, LB, CB, CB, RB, GK)
    val _4222 = listOf(CAM, ST, ST, CAM, CDM, CDM, LB, CB, CB, RB, GK)
    val _4231 = listOf(CAM, ST, CAM, CAM, CDM, CDM, LB, CB, CB, RB, GK)
    val _4321 = listOf(CAM, ST, CAM, CM, CM, CM, LB, CB, CB, RB, GK)
    val _442 = listOf(LM, ST, ST, RM, CM, CM, LB, CB, CB, RB, GK)
    val _41212 = listOf(ST, ST, CAM, LM, RM, CDM, LB, CB, CB, RB, GK)
    val _5212 = listOf(ST, CAM, ST, CM, CM, LB, CB, CB, CB, RB, GK)
}
