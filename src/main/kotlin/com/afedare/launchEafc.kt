package com.afedare

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import java.nio.file.Path

fun launchEafc(): Page {
    val playwright = Playwright.create()
    val userData = "/Users/fedache/Documents/eafc_profile"

    val userDataPath = Path.of(userData)
    val browser = playwright.chromium().launchPersistentContext(
        userDataPath, BrowserType.LaunchPersistentContextOptions().setHeadless(false)
            .setViewportSize(null)
    )
//    (Browser.NewPageOptions().setViewportSize(null))

    val page = browser.newPage()
    page.navigate("https://www.ea.com/ea-sports-fc/ultimate-team/web-app/")
    return page
}
fun main() {
    launchEafc()
}
