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

    val countdownGUIIcon: ItemStack
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
                        +literalText("Öffnet das Countdown Einstellungsmenü.") {
                            color = KColors.GRAY
                            italic = true
                        }
                    }
                }
            }
        }

    val countdownIcon: ItemStack
        get() {
            return itemStack(Material.CLOCK) {
                meta {
                    name = literalText("Countdown") {
                        color = KColors.GOLDENROD
                        italic = false
                    }

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

    val addCountdownTimeIcon: ItemStack
        get() {
            return itemStack(Material.GREEN_WOOL) {
                meta {
                    name = literalText("+1 Minute") {
                        color = KColors.GREEN
                        italic = false
                    }

                    addLore {
                        +literalText("Fügt eine Minute zum Countdown hinzu.") {
                            color = KColors.GRAY
                            italic = true
                        }
                        +literalText(" ")
                        +literalText("Aktuelle Zeit: ") {
                            color = KColors.GRAY
                            italic = false
                        }
                        +SettingsManager.countdownTimeComponent
                    }
                }
            }
        }

    val reduceCountdownTimeIcon: ItemStack
        get() {
            return itemStack(Material.RED_WOOL) {
                meta {
                    name = literalText("-1 Minute") {
                        color = KColors.RED
                        italic = false
                    }

                    addLore {
                        +literalText("Entfernt eine Minute des Countdowns.") {
                            color = KColors.GRAY
                            italic = true
                        }
                        +literalText(" ")
                        +literalText("Aktuelle Zeit: ") {
                            color = KColors.GRAY
                            italic = false
                        }
                        +SettingsManager.countdownTimeComponent
                    }
                }
            }
        }
}