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

    val startIcon: ItemStack
        get() {
            return itemStack(Material.NETHER_STAR) {
                meta {
                    name = literalText("Start") {
                        color = KColors.GREEN
                        italic = false
                    }
                    addLore {
                        +literalText(" ")
                        +literalText("Startet die konfigurierte ") {
                            color = KColors.GRAY
                            italic = true
                        }
                        +literalText("Bingo Runde.") {
                            color = KColors.GRAY
                            italic = true
                        }
                    }
                }
            }
        }

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
            return itemStack(Material.REDSTONE) {
                meta {
                    name = literalText("Countdown") {
                        color = KColors.GOLD
                        italic = false
                    }

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

    val resetCountdownIcon: ItemStack
        get() {
            return itemStack(Material.RED_CONCRETE) {
                meta {
                    name = literalText("Countdown zurücksetzen") {
                        color = KColors.DARKRED
                        italic = false
                    }

                    addLore {
                        +literalText("Setzt den Countdown auf ") {
                            color = KColors.GRAY
                            italic = true
                        }
                        +literalText("30 Minuten zurück.") {
                            color = KColors.GRAY
                            italic = true
                        }
                    }
                }
            }
        }

    val backIcon: ItemStack
        get() {
            return itemStack(Material.BREWING_STAND) {
                meta {
                    name = literalText("Zurück") {
                        color = KColors.WHITE
                        italic = false
                        bold = true
                    }

                    addLore {
                        +literalText("Bringt dich eine Seite zurück") {
                            color = KColors.GRAY
                            italic = true
                        }
                    }
                }
            }
        }

    val teamsComingSoonIcon: ItemStack
        get() {
            return itemStack(Material.BARRIER) {
                meta {
                    name = literalText("Team System") {
                        color = KColors.DARKRED
                        italic = false
                    }

                    addLore {
                        +literalText("Coming Soon") {
                            color = KColors.GRAY
                            italic = true
                        }
                    }
                }
            }
        }

    val teamsIcon: ItemStack
        get() {
            return itemStack(Material.YELLOW_WOOL) {
                meta {
                    name = literalText("Team Verwaltung") {
                        color = KColors.YELLOW
                        italic = false
                    }

                    addLore {
                        +literalText("Öffnet das Team Verwaltungs Menü") {
                            color = KColors.GRAY
                            italic = true
                        }
                    }
                }
            }
        }

    val createTeamIcon: ItemStack
        get() {
            return itemStack(Material.LIME_WOOL) {
                meta {
                    name = literalText("Erstelle ein neues Team") {
                        color = KColors.LIMEGREEN
                        italic = false
                    }

                    addLore {
                        +literalText("Erstellt ein neues Team.") {
                            color = KColors.GRAY
                            italic = true
                        }
                        +literalText(" ")
                        +literalText("Aktuelle Anzahl Teams: ") {
                            color = KColors.GRAY
                            italic = false
                        }
                        +SettingsManager.teamCountComponent
                    }
                }
            }
        }

    val getTeamsIcon: ItemStack
        get() {
            return itemStack(Material.GRAY_WOOL) {
                meta {
                    name = literalText("Verwalte bestehende Teams.") {
                        color = KColors.LIMEGREEN
                        italic = false
                    }

                    addLore {
                        +literalText("Verwaltet bereits ") {
                            color = KColors.GRAY
                            italic = true
                        }
                        +literalText("bestehende Teams.") {
                            color = KColors.GRAY
                            italic = true
                        }
                        +literalText(" ")
                        +literalText("Aktuelle Anzahl Teams: ") {
                            color = KColors.GRAY
                            italic = false
                        }
                        +SettingsManager.teamCountComponent
                    }
                }
            }
        }
}