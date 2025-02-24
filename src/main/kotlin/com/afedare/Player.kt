package com.afedare

data class Player(
    var rarity: String = "",
    var rating: Int = 0,
    var fullName: String = "",
    var knownAs: String = "",
    var dateOfBirth: String = "",
    var height: String = "",
    var preferredPosition: String = "",
    var alternatePositions: String = "",
    var preferredFoot: String = "",
    var weakFoot: String = "",
    var skillMoves: String = "",
    var nation: String = "",
    var club: String = "",
    var league: String = "",
    var chemistryStyle: String = "",
    var numberOfOwners: String = "",
    var gamesPlayed: String = "",
    var goalsScored: String = "",
    var yellowCards: String = "",
    var redCards: String = "",
) {
    fun hasPosition(position: String): Boolean {
        return position == preferredPosition ||
                alternatePositions.split(",").contains(position)
    }

    fun name() = if (knownAs != "---") {
        knownAs
    } else {
        fullName
    }
}