package com.afedare

import com.afedare.Formation._41212
import com.afedare.Formation._4213
import com.afedare.Formation._4222
import com.afedare.Formation._4231
import com.afedare.Formation._4321
import com.afedare.Formation._433
import com.afedare.Formation._442
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int

fun main(args: Array<String>) = SBC()
    .subcommands(GenSBC(), CraftSBC(), FindPlayers())
    .main(args)

class SBC : CliktCommand() {
    override fun run() = Unit
}


class GenSBC : CliktCommand(name = "gen") {
    override fun help(context: Context): String = "Try solve sbc with restrictions and replace player found"
    private val title: String by argument(help = "SBC Title")
    private val formation: String by option(help = "SBC formation").default("433")
    private val restrictions: String by option(help = "SBC Restriction").default("min33Chem")
    private val sbcIndex: Int? by option(help = "Sub sbc index").int()

    override fun run() {
        val restrictList = restrictions.split(Regex(" (?=\\w+=)"))

        val iRestrict: List<IRestrict> = restrictList.map { restriction ->
            val keyValArray = restriction.split("=")
            val subject = keyValArray[0]
            val value = keyValArray[1]
            val iRestrict = when (subject) {
                "minClub" -> {
                    IRestrict { _, players -> minXClubs(players, value.toDouble()) }
                }

                "maxClub" -> {
                    IRestrict { _, players -> maxXClubs(players, value.toDouble()) }
                }

                "minRare" -> {
                    IRestrict { _, players -> minXRare(players, value.toDouble()) }
                }

                "minRating" -> {
                    IRestrict { _, players -> minRating(players, value.toDouble()) }
                }

                "maxRating" -> {
                    IRestrict { _, players -> maxRating(players, value.toDouble()) }
                }

                "minPlayerRating" -> {
                    IRestrict { _, players -> minRatingPlayer(players, value.toInt()) }
                }

                "maxPlayerRating" -> {
                    IRestrict { _, players -> maxRatingPlayer(players, value.toInt()) }
                }

                "minChem" -> {
                    IRestrict { formation, players -> minChem(formation, players, value.toInt()) }
                }

                "minNation" -> {
                    IRestrict { _, players -> minXNation(players, value.toDouble()) }
                }

                "minLeague" -> {
                    IRestrict { _, players -> minLeague(players, value.toDouble()) }
                }

                "maxLeague" -> {
                    IRestrict { _, players -> maxLeague(players, value.toDouble()) }
                }

                "maxNation" -> {
                    IRestrict { _, players -> maxNation(players, value.toDouble()) }
                }

                "minSameNation" -> {
                    IRestrict { _, players -> minXSamenation(players, value.toDouble()) }
                }

                "minSameClub" -> {
                    IRestrict { _, players -> minXSameclub(players, value.toDouble()) }
                }

                "maxSameClub" -> {
                    IRestrict { _, players -> maxXSameClub(players, value.toDouble()) }
                }

                "minSameLeague" -> {
                    IRestrict { _, players -> minXSameLeague(players, value.toDouble()) }
                }

                "maxSameLeague" -> {
                    IRestrict { _, players -> maxXSameLeague(players, value.toDouble()) }
                }

                "minFromNation" -> {
                    val (nation, count) = value.split(":")
                    IRestrict { _, players -> minXFromNation(players, nation, count.toDouble()) }
                }

                "minFromLeague" -> {
                    val (league, count) = value.split(":")
                    IRestrict { _, players -> minXFromLeague(players, league, count.toDouble()) }
                }

                "maxFromLeague" -> {
                    val (league, count) = value.split(":")
                    IRestrict { _, players -> maxXFromLeague(players, league, count.toDouble()) }
                }

                "minFromClub" -> {
                    val (club, count) = value.split(":")
                    IRestrict { _, players -> minXFromClub(players, club, count.toDouble()) }
                }

                else -> {
                    throw UnsupportedOperationException("")
                }
            }

            iRestrict
        }

        val formPositions = when (formation) {
            "433" -> _433
            "4213" -> _4213
            "4222" -> _4222
            "4231" -> _4231
            "4321" -> _4321
            "442" -> _442
            "41212" -> _41212
            else -> _433
        }

        val result = geneticAlgo(
            formation = formPositions, allPlayers = readClub(), restrictions = iRestricts
        )
        println("\nBest SBC Squad Found:")
        println("\nChemistry: ${chemistry(formPositions, result)}")

        val positionPlayers = playersToPosition(result, formPositions)
        positionPlayers.forEach { (position, player) ->
            val name = player.name()
            println("$name /$position/${player.club} /${player.nation} /${player.rating}")
        }
        println("\nSquad Rating: ${result.sbcAverage()}")
        val page = launchEafc()
        page.apply {
            goToSbc()
            sbcTitle(title)
            if (sbcIndex != null) {
                selectSubSbc(sbcIndex!!)
            }
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
        }

    }
}

class FindPlayers : CliktCommand(name = "findPlayers") {
    override fun run() {
        val page = launchEafc()
        page.use {
            findAllPlayers(page)
        }
    }
}

class CraftSBC : CliktCommand(name = "craft") {
    override fun help(context: Context): String =
        "Craft an sbc that requires 2 player quality max like gold and gold rare"

    private val title: String by argument(help = "SBC Title")
    private val count: Int by option(help = "Repeat count times").int().default(3)

    private val quality: String? by option(help = "Populate sbc with quality").default("Bronze")
    private val rarity: String? by option(help = "Populate sbc with rarity")
    private val quality2: String? by option(help = "Other sbc with quality")
    private val rarity2: String? by option(help = "Other sbc with rarity")
    private val card2Count: String? by option(help = "Other rarity count").default("1")

    override fun run() {
        val subQualityList: List<String>? = quality?.split(",")
        val subQuality2List = quality2?.split(",")?.filter { it.isNotBlank() }
        val subRarityList = rarity?.split(",")?.filter { it.isNotBlank() }
        val subRarity2List = rarity2?.split(",")?.filter { it.isNotBlank() }
        val card2CountList = card2Count?.split(",")?.filter { it.isNotBlank() }
        val subSbcCount = subQualityList?.size ?: 0
        val page = launchEafc()
        page.use {
            page.apply {
                goToSbc()
                if (subSbcCount > 1)
                    sbcTitle(title)
                for (i in 0 until count) {
                    if (subSbcCount <= 1)
                        sbcTitle(title)
                    for (k in 0 until subSbcCount) {
                        try {
                            if (subSbcCount > 1)
                                selectSubSbc(k)
                            selectSquadBuilder()
                            ignorePostions()
                            highLowToLowHigh()
                            selectCardType(subQualityList?.get(k)!!, subRarityList?.get(k))
                            clickBuild()
                            var j = 0
                            if (subQuality2List?.get(k) != null) {
                                while (j < card2CountList?.get(k)!!.toInt()) {
                                    try {
                                        swapPlayer(j)
                                        highLowToLowHigh()
                                        selectCardType(subQuality2List[k], subRarity2List?.get(k))
                                        clearPositions()
                                        select1stPlayerSearch()
                                        j++
                                    } catch (ex: Exception) {
                                        println("Unable to swap @ index $j")
                                    }
                                }
                            }
                            clickSubmit()
                            claimRewards()
                        } catch (ex: Exception) {
                            println("Unable to complete: $k sbc\n$ex")
                        }
                    }
                    if (subSbcCount > 1)
                        claimRewards()
                }
            }
        }
    }
}