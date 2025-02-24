package com.afedare

import com.microsoft.playwright.*

fun generatePlaywright() {
    CLI.main(
        arrayOf("codegen", "https://www.ea.com/ea-sports-fc/ultimate-team/web-app/")
//        arrayOf("codegen", "install")
    )
}

fun main() {
    generatePlaywright()
}
