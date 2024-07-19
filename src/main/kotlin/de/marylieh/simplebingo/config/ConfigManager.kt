package de.marylieh.simplebingo.config

import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException

object ConfigManager {

    private lateinit var file: File
    private lateinit var configuration: YamlConfiguration

    fun config() {
        val dir = File("./plugins/SimpleBingo")

        if (!dir.exists()) {
            dir.mkdirs()
        }

        file = File(dir, "config.yml")

        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        configuration = YamlConfiguration.loadConfiguration(file)
    }

    val config
        get() = YamlConfiguration.loadConfiguration(file)

    fun save() {
        try {
            config.save(file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun reload(): Boolean {
        try {
            config.load(file)
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
            return false
        }
    }
}