package de.marylieh.simplebingo.game

import de.marylieh.simplebingo.config.ConfigManager
import de.marylieh.simplebingo.teams.Team
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.items.addLore
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory

object GamestateManager {

    var tmp = ""

    var passive = false

    var bingoItems: MutableList<Material> = mutableListOf()

    var teams: MutableList<Team> = mutableListOf()

    var currentGameState = GameStates.LOBBY_PHASE

    val backpackEnabled
        get() = ConfigManager.config.getBoolean("command.backpack.enabled")

    val backpackSlots
        get() = ConfigManager.config.getInt("command.backpack.slots")

    val excludedItems: List<String>
        get() = ConfigManager.config.getList("bingo.invalidItems") as List<String>

    val maxItems
        get() = ConfigManager.config.getInt("bingo.maxItems")

    val countdownHighestCountWin
        get() = ConfigManager.config.getBoolean("bingo.countdownHighestCountWin")

    var debug = true

    var winner = ""

    var timeWon = false

    var itemsWon = 0

    var timer = false

    var countdown = false

    var countdownFinished = false

    var timerPaused = true

    var timeInSeconds = 0

    fun countFoundItems(player: Player): Int {
        var count = 0

        bingoItems.forEach {
            if (player.hasMetadata(it.name)) {
                count += 1
            }
        }

        return count
    }

    fun getBingoInventory(player: Player) : Inventory {
        if (maxItems > 9) {
            val inv = Bukkit.createInventory(null, maxItems, Component.text("Bingo | Items", NamedTextColor.GREEN))

            bingoItems.forEach {

                if (player.hasMetadata(it.name)) {
                    inv.addItem(itemStack(Material.GREEN_STAINED_GLASS_PANE) {
                        meta {
                            name = literalText(it.name) {
                                color = KColors.GREEN
                                italic = false
                            }
                            addLore {
                                +literalText("")
                                +literalText("Bereits gefunden") {
                                    color = KColors.GREEN
                                    italic = true
                                }
                            }
                        }
                    })
                } else {
                    inv.addItem(itemStack(it) {
                        meta {
                            addLore {
                                +literalText("")
                                +literalText("Dieses Item muss noch gefunden werden") {
                                    color = KColors.RED
                                    italic = true
                                }
                            }
                        }
                    })
                }
            }
            return inv
        }
        val inv = Bukkit.createInventory(null, InventoryType.DROPPER, Component.text("Bingo | Items", NamedTextColor.GREEN))

        bingoItems.forEach {

            if (player.hasMetadata(it.name)) {
                inv.addItem(itemStack(Material.GREEN_STAINED_GLASS_PANE) {
                    meta {
                        name = literalText(it.name) {
                            color = KColors.GREEN
                            italic = false
                        }
                        addLore {
                            +literalText("")
                            +literalText("Bereits gefunden") {
                                color = KColors.GREEN
                                italic = true
                            }
                        }
                    }
                })
            } else {
                inv.addItem(itemStack(it) {
                    meta {
                        addLore {
                            +literalText("")
                            +literalText("Dieses Item muss noch gefunden werden") {
                                color = KColors.RED
                                italic = true
                            }
                        }
                    }
                })
            }
        }
        return inv
    }
}

enum class GameStates {
    LOBBY_PHASE,
    GAME_PHASE,
    FINAL_PHASE
}