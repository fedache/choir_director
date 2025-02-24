plugins {
    kotlin("jvm") version "2.0.0"
    application
}

group = "com.afedare"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.afedare.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.microsoft.playwright:playwright:1.46.0")
    implementation("com.github.ajalt.clikt:clikt:5.0.1")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}