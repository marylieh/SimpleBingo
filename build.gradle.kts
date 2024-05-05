plugins {
    kotlin("jvm") version "1.9.21"
    id("io.papermc.paperweight.userdev") version "1.7.0"
}

group = "de.marylieh"
version = "2.0-RELEASE"

repositories {
    mavenCentral()
}

dependencies {
    paperDevBundle("1.20.6-R0.1-SNAPSHOT")
    implementation("net.axay:kspigot:1.20.3")
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