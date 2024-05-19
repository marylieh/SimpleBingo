package de.marylieh.simplebingo.game

import de.marylieh.simplebingo.config.ConfigManager
import de.marylieh.simplebingo.teams.Team
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit

object GamestateManager {

    var tmp = ""

    var passive = false

    var teams: MutableList<Team> = mutableListOf()

    var currentGameState = GameStates.LOBBY_PHASE

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

enum class GameStates {
    LOBBY_PHASE,
    GAME_PHASE,
    FINAL_PHASE
}