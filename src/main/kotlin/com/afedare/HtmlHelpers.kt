package com.afedare

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page

fun Page.swapPlayer(index: Int) {
    Thread.sleep(200)
    locator("[index='$index'].ut-squad-slot-view div.player").click()
    click("span.btn-text:has-text('Swap Player')")
}

fun Page.clearPositions() {
    val position = locator(".inline-list-select.ut-search-filter-control").nth(3)
    position.locator(".flat.ut-search-filter-control--row-button").click()
}

fun Page.clickBuild() {
    clickActionBtn("Build")
}

fun Page.clickActionBtn(name: String) {
    click(".btn-standard.call-to-action:has-text('$name')")
}

fun Page.clickSubmit() {
    locator("button.ut-squad-tab-button-control.actionTab.right.call-to-action").click()
}

fun Page.claimRewards() {
    locator("button.btn-standard.call-to-action:has-text('Claim Rewards')").click()
}

fun Page.selectSquadBuilder() {
    val squadBuilder = ".btn-standard:has-text('Use Squad Builder')"
    this.waitForSelector(squadBuilder)
    this.click(squadBuilder)
}

fun Page.ignorePostions() {
    val ignore = this.locator("div.ut-toggle-control-group-view .ut-toggle-cell-view").nth(3)
    val ignoreClass = ignore.locator(".ut-toggle-control").getAttribute("class")
    if (!ignoreClass.contains("toggled")) {
        ignore.click()
    }
}

fun Page.highLowToLowHigh() {
    click(".label:has-text('Rating High to Low')")
    click("ul.inline-list li:has-text('Rating Low to High')")
}


fun Page.sbcTitle(title: String) {
    val element = locator("//h1[@class='tileTitle' and text()='$title']")
    element.scrollIntoViewIfNeeded()
    element.click()
}

fun Page.selectQuality(quality: String) {
    locator(".inline-list-select.ut-search-filter-control").nth(1).click()
    click("ul.inline-list li:has-text('$quality')")
}

fun Page.goToSbc() {
    val sbcClass = ".ut-tab-bar-item.icon-sbc"
    waitForSelector(sbcClass)
    click(sbcClass)
}

fun Page.clickStartChallenge() {
    clickActionBtn("Challenge")
}

fun Page.selectSubSbc(index: Int) {
    val sbcList = locator("div.ut-sbc-challenge-table-row-view")
    sbcList.nth(index).click()
    clickStartChallenge()
}

fun Page.workTab() {
    locator("button.ut-squad-tab-button-control.workTab").click()
}

fun Page.selectSub(i: Int): Locator =
    locator(".ut-squad-slot-dock-view--slot-container div:nth-child(${i + 1}) > .small")

fun Page.select1stPlayerSearch() {
    searchClick()
    val players = locator(".paginated-item-list.ut-pinned-list ul li")
    val player1 = players.nth(0)
    val addPlayer1 = player1.locator(".ut-image-button-control.btnAction.add")
    addPlayer1.click()
}

fun Page.selectPlayerSearch(index: Int) {
    locator("button.btn-standard.call-to-action:has-text('Search')").click()
    val players = locator(".paginated-item-list.ut-pinned-list ul li")
    val player1 = players.nth(0)
    val addPlayer1 = player1.locator(".ut-image-button-control.btnAction.add")
    addPlayer1.click()
}

fun Page.selectCardType(quality: String, rarity: String?) {
    locator(".inline-list-select.ut-search-filter-control").nth(1).click()
    click("ul.inline-list li:has-text('$quality')")

    if (rarity != null) {
        locator(".inline-list-select.ut-search-filter-control").nth(2).click()
        click("ul.inline-list li:has-text('$rarity')")
    }
}

fun Page.selectConceptPlayers() {
    locator(".inline-list-select.ut-search-filter-control").nth(0).click()
    click("ul.inline-list li:has-text('Concept Players')")
}

fun Page.searchPlayer(name: String) {
    val input = locator("div.inline-list-select.ut-player-search-control input.ut-text-input-control")
    input.fill(name)

    val searchList = locator("ul.ut-button-group.playerResultsList button")
    searchList.nth(0).click()

    searchClick()
    val players = locator(".paginated-item-list.ut-pinned-list ul li")
    val player1 = players.nth(0)
    val addPlayer1 = player1.locator(".ut-image-button-control.btnAction.add")
    addPlayer1.click()
    Thread.sleep(100)
}

private fun Page.searchClick() {
    locator("button.btn-standard.call-to-action:has-text('Search')").click()
}