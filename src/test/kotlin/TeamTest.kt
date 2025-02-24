import com.afedare.Formation._433
import com.afedare.Player
import com.afedare.chemistry
import com.afedare.generateSquad
import com.afedare.readClub
//import com.afedare.Team
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class TeamTest {
    @Test
    fun testGenerateSquad() {
        val squad = generateSquad(_433, readClub())
        for (player in squad) {
            println("player ${player.fullName} // ${player.league} // ${player.club} // ${player.nation}")
        }
        println(chemistry(_433, squad))
    }

    @Test
    fun testChemistry() {
        val players = listOf(
            Player(
                fullName = "Antoine Griezmann",
                club = "Atlético de Madrid",
                league = "LALIGA EA SPORTS",
                nation = "France",
                rating = 90,
                preferredPosition = "ST"
            ),
            Player(
                fullName = "Iñaki Williams",
                club = "Athletic Club",
                league = "LALIGA EA SPORTS",
                nation = "Ghana",
                rating = 86,
                preferredPosition = "RM",
                alternatePositions = "RW, ST"
            ),
            Player(
                fullName = "Nico Williams",
                club = "Athletic Club",
                league = "LALIGA EA SPORTS",
                nation = "Spain",
                rating = 87,
                preferredPosition = "LM",
                alternatePositions = "LW, RM"
            ),
            Player(
                fullName = "Martin Ødegaard", club = "Arsenal", league = "Premier League", nation = "Norway",
                rating = 88,
                preferredPosition ="CM",
                alternatePositions = "CAM"
            ),
            Player(
                fullName = "Joelinton", club = "Newcastle United", league = "Premier League", nation = "Brazil",
                rating = 91,
                preferredPosition = "CM",
                alternatePositions = "LW"
            ),
            Player(
                fullName = "Mohamed Salah", club = "Liverpool", league = "Premier League", nation = "Egypt",
                rating = 86,
                preferredPosition = "RM",
                alternatePositions = "RW"
            ),
            Player(
                fullName = "Destiny Udogie",
                club = "Tottenham Hotspur",
                league = "Premier League",
                nation = "Italy",
                rating = 81,
                preferredPosition = "LB"
            ),
            Player(
                fullName = "Micky van de Ven",
                club = "Tottenham Hotspur",
                league = "Premier League",
                nation = "Netherlands",
                rating = 88,
                preferredPosition = "CB",
            ),
            Player(
                fullName = "David Alaba", club = "Real Madrid", league = "LALIGA EA SPORTS", nation = "Austria",
                rating = 84,
                preferredPosition = "CB",
                alternatePositions = "LB"
            ),
            Player(
                fullName = "Marcos Llorente",
                club = "Atlético de Madrid",
                league = "LALIGA EA SPORTS",
                nation = "Spain",
                rating = 80,
                preferredPosition = "RM",
                alternatePositions = "CM, RB, RW"
            ),
            Player(
                fullName = "Thibaut Courtois",
                club = "Real Madrid",
                league = "LALIGA EA SPORTS",
                nation = "Belgium",
                rating = 87,
                preferredPosition = "GK",
            )
        )
        val chem = chemistry(_433, players)
        if (chem != 24) {
            throw AssertionError("Invalid team chemistry")
        }
    }
}