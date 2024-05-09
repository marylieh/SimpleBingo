plugins {
    kotlin("jvm") version "1.9.21"
    id("io.papermc.paperweight.userdev") version "1.7.0"
}

group = "de.marylieh.simplebingo"
version = "2.0-RELEASE"

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
}

dependencies {
    paperDevBundle("1.20.3-R0.1-SNAPSHOT")
    implementation("net.axay:kspigot:1.20.3")

    compileOnly("com.mojang", "brigadier", "latest")
}

tasks {
    build {
        dependsOn(reobfJar)
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(21)
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "21"
    }
}
kotlin {
    jvmToolchain(21)
}