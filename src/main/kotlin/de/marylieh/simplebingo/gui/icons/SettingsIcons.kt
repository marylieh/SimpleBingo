package de.marylieh.simplebingo.gui.icons

import de.marylieh.simplebingo.gui.guimanager.SettingsManager
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.items.*
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
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

    val countdownIcon: ItemStack
        get() {
            return itemStack(Material.CLOCK) {
                addEnchantment(Enchantment.DURABILITY, 1)
                meta {
                    name = literalText("Countdown") {
                        color = KColors.GOLDENROD
                        italic = false
                    }

                    flag(ItemFlag.HIDE_ENCHANTS)
                    addLore {
                        +literalText("Aktiviert/Deaktiviert einen Countdown.") {
                            color = KColors.GRAY
                            italic = true
                        }
                        +literalText("Ist inkompatibel mit der Timer option.") {
                            color = KColors.GRAY
                            italic = true
                        }
                        +literalText(" ")
                        +literalText("Aktueller Status: ") {
                            color = KColors.GRAY
                            italic = false
                        }
                        +SettingsManager.countdownStateComponent
                    }
                }
            }
        }
}