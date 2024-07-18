package de.marylieh.simplebingo.game

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import java.util.*

object BackpackManager {

    private val backpacks: MutableMap<UUID, Inventory> = mutableMapOf()

    fun getBackpack(player: Player): Inventory {
        return backpacks.computeIfAbsent(player.uniqueId) {
            Bukkit.createInventory(null, GamestateManager.backpackSlots, Component.text("Backpack", NamedTextColor.BLUE).decoration(TextDecoration.BOLD, true))
        }
    }

    fun removeBackpack(player: Player) {
        backpacks.remove(player.uniqueId)
    }
}