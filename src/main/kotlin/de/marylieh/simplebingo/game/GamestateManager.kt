package de.marylieh.simplebingo.game

import de.marylieh.simplebingo.config.ConfigManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit

object GamestateManager {

    var passive = false

    val backpackEnabled
        get() = ConfigManager.config.getBoolean("command.backpack.enabled")

    private val backpackSlots
        get() = ConfigManager.config.getInt("command.backpack.slots")

    val backpack = Bukkit.createInventory(null, backpackSlots, Component.text("Backpack", NamedTextColor.GRAY).decoration(
        TextDecoration.BOLD, true))

    var timer = false

    var countdown = false

    var timerPaused = true

    var timeInSeconds = 0

}