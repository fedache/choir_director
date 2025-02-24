package com.afedare

import com.microsoft.playwright.Page

fun Page.justQuality(quality: String) {
    selectSquadBuilder()
    ignorePostions()
    highLowToLowHigh()
    selectQuality(quality)
    clickBuild()
    Thread.sleep(200)
    clickSubmit()
    claimRewards()
}
