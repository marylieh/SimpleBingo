package de.marylieh.simplebingo

import net.axay.kspigot.main.KSpigot

class SimpleBingo : KSpigot() {

    companion object {
        lateinit var Instance: SimpleBingo; private set
    }

    override fun load() {
        Instance = this
    }

    override fun startup() {

    }

    override fun shutdown() {

    }

    val simpleBingo by lazy { Instance }

}
