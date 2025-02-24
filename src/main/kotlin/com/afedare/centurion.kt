package com.afedare

import com.microsoft.playwright.*
import com.microsoft.playwright.options.AriaRole
import java.nio.file.Path
import java.util.regex.Pattern

fun main() {
    Playwright.create().use { playwright ->
        val userData = "/Users/fedache/Documents/eafc_profile"
        val userDataPath = Path.of(userData)
        val browser = playwright.chromium().launchPersistentContext(
            userDataPath, BrowserType.LaunchPersistentContextOptions().setHeadless(false)
        )
        val page = browser.newPage()

        page.navigate("https://www.ea.com/ea-sports-fc/ultimate-team/web-app/")
        val clubClass = ".ut-tab-bar-item.icon-club"
        page.waitForSelector(clubClass)
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName(" SBC")).click()
        page.getByText("Centurions Crafting UpgradeComplete this challenge to progress towards earning").click()
        page.locator("div:nth-child(12) > .small").first().click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Add Player")).click()
        page.getByText("Highest Quick Sell").click()
        page.getByText("Rating Low to High").click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("")).click()
        page.locator("div").filter(Locator.FilterOptions().setHasText(Pattern.compile("^My Club$"))).nth(2).click()
        page.getByText("SBC Storage").click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Search")).click()
        page.locator(".paginated > li > .ut-image-button-control").first().click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("")).nth(3).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Search")).click()
        page.locator(".paginated > li > .ut-image-button-control").first().click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("")).nth(4).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Search")).click()
        page.locator("div").filter(Locator.FilterOptions().setHasText(Pattern.compile("^Club Search$"))).getByRole(AriaRole.BUTTON).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("")).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Search")).click()
        page.locator("li").filter(Locator.FilterOptions().setHasText("80RMAVGN/ASM5WF (R)")).getByRole(AriaRole.BUTTON).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("")).nth(4).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("")).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Search")).click()
        page.locator("li").filter(Locator.FilterOptions().setHasText("80RBAVGN/ASM3WF (L)3PACDRISHODEFPASPHYRBMALESP 1RSOStand Tackle79Def.")).getByRole(AriaRole.BUTTON).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("")).nth(4).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("")).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Search")).click()
        page.locator("li").filter(Locator.FilterOptions().setHasText("80CBAVGN/ASM2WF (L)")).getByRole(AriaRole.BUTTON).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("")).nth(4).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("")).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Search")).click()
        page.locator(".paginated > li > .ut-image-button-control").first().click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("")).nth(4).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("")).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Search")).click()
        page.locator(".paginated > li > .ut-image-button-control").first().click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName(" Submit")).click()
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Claim Rewards")).click()
        page.getByRole(AriaRole.HEADING, Page.GetByRoleOptions().setName("Centurions Crafting Upgrade")).click()
    }
}
