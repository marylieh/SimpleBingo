package de.marylieh.simplebingo.gui.icons

import de.marylieh.simplebingo.gui.guimanager.SettingsManager
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.items.addLore
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object SettingsIcons {

    val timerIcon: ItemStack
        get() {
            return itemStack(Material.CLOCK) {
                meta {
                    name = literalText("Timer") {
                        color = KColors.GOLD
                        italic = false
                    }
                    addLore {
                        +literalText("Aktiviert/Deaktiviert einen fortlaufenden Timer, ") {
                            color = KColors.GRAY
                            italic = true
                        }
                        +literalText("welcher beginnt sobald die Runde gestartet wird.") {
                            color = KColors.GRAY
                            italic = true
                        }
                        +literalText(" ")
                        +literalText("Aktueller Status: ") {
                            color = KColors.GRAY
                            italic = false
                        }
                        +SettingsManager.timerStateComponent
                    }
                }
            }
        }
}