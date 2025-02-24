package com.afedare

import com.google.gson.Gson
import com.microsoft.playwright.Page
import kotlinx.coroutines.runBlocking
import java.io.File

const val workDir = "/Users/fedache/Documents/workdir"
const val clubPaletool = "/Users/fedache/Downloads/club-analyzer.csv"
const val clubFilename = "$workDir/club_players.json"

fun main() = runBlocking {
    val page = launchEafc()
    findAllPlayers(page)
}

fun findAllPlayers(page: Page) {
    val clubClass = ".ut-tab-bar-item.icon-club"
    page.waitForSelector(clubClass)
    page.click(clubClass)
    page.click("text=Players")
    page.click("text=Search")
    val searchControl = ".inline-list-select.ut-drop-down-control"
    page.click(searchControl)
    page.click("text=Rating High to Low")
    page.click("button:has-text('Search')")
    val playersList = page.locator("ul.paginated > li.listFUTItem")
    val playerSeen = mutableListOf<Player>()
    while (true) {
        for (i in 0 until playersList.count()) {
            try {
                val playerItem = playersList.nth(i)
                playerItem.click()
                page.click("text=Player Bio")
                Thread.sleep(50)
                val playerInfoList = page.locator("ul.ut-item-bio-list-view > li.ut-item-bio-row-view")
                val hasTempStatus = page.locator("ul.ut-item-bio-list-view > li.ut-temporary-item-row").count()
                if (hasTempStatus > 0) {
                    continue
                }

                val player = Player()
                val rating = playerItem.locator(".rating").textContent()
                player.rating = rating.toInt()
                for (j in 0 until playerInfoList.count()) {
                    val playerInfo = playerInfoList.nth(j)
                    val propName = playerInfo.locator("h1").textContent().trim()
                    val propValue = playerInfo.locator("h2").textContent().trim()
                    when (propName) {
                        "Rarity" -> player.rarity = propValue
                        "Full Name" -> player.fullName = propValue
                        "Known As" -> player.knownAs = propValue
                        "Date of Birth" -> player.dateOfBirth = propValue
                        "Height" -> player.height = propValue
                        "Preferred Position" -> player.preferredPosition = propValue
                        "Preferred Foot" -> player.preferredFoot = propValue
                        "Weak Foot" -> player.weakFoot = propValue
                        "Skill Moves" -> player.skillMoves = propValue
                        "Nation" -> player.nation = propValue
                        "Club" -> player.club = propValue
                        "League" -> player.league = propValue
                        "Chemistry Style" -> player.chemistryStyle = propValue
                        "Number of Owners" -> player.numberOfOwners = propValue
                        "Games Played (Other Clubs / Your Club)" -> {
                            player.gamesPlayed = propValue
                        }

                        "Goals Scored" -> player.goalsScored = propValue
                        "Yellow Cards (Other Clubs / Your Club)" -> {
                            player.yellowCards = propValue
                        }

                        "Red Cards (Other Clubs / Your Club)" -> {
                            player.redCards = propValue
                        }

                        "Alternate positions" -> {
                            player.alternatePositions = propValue
                        }

                        else -> println("Unknown property: $propName")
                    }
                }
                playerSeen.add(player)
            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
        val nextClass = ".flat.pagination.next"
        val nextSelect = page.locator(nextClass)
        if (!nextSelect.isHidden) {
            nextSelect.click()
        } else {
            break
        }
    }

    val gson = Gson()
    val playerJson = gson.toJson(playerSeen)

    File(clubFilename).writeText(playerJson)
}

fun readClub(): List<Player> {
    val jsonFromFile = File(clubFilename).readText()
    val playerListType = object : com.google.gson.reflect.TypeToken<List<Player>>() {}.type
    val gson = Gson()
    return gson.fromJson(jsonFromFile, playerListType)
}

