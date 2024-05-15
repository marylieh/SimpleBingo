package de.marylieh.simplebingo.gui

import de.marylieh.simplebingo.Manager
import de.marylieh.simplebingo.gui.guimanager.SettingsManager
import de.marylieh.simplebingo.gui.icons.SettingsIcons
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.gui.GUIType
import net.axay.kspigot.gui.Slots
import net.axay.kspigot.gui.kSpigotGUI
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.entity.Player

class SettingsGUI {

    val settingsGUI = kSpigotGUI(GUIType.SIX_BY_NINE) {
        title = literalText("Settings") {
            color = KColors.LIMEGREEN
        }
        page(1) {
            placeholder(Slots.Border, itemStack(Material.WHITE_STAINED_GLASS_PANE) { meta { name = null } })
            button(Slots.RowFiveSlotTwo, SettingsIcons.timerIcon) { clickEvent ->

                (clickEvent.bukkitEvent.whoClicked as? Player)?.let { player ->
                    SettingsManager.switchTimer()
                    player.sendMessage(Manager.prefix
                        .append(Component.text("Der Timer ist nun ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                        .append(SettingsManager.timerStateComponent))
                    player.closeInventory()
                }
            }

            button(Slots.RowFiveSlotFour, SettingsIcons.countdownIcon) { clickEvent ->

                (clickEvent.bukkitEvent.whoClicked as? Player)?.let { player ->
                    SettingsManager.switchCountdown()
                    player.sendMessage(Manager.prefix
                        .append(Component.text("Der Countdown ist nun ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                        .append(SettingsManager.countdownStateComponent))
                    player.closeInventory()
                }

            }

        }
    }
}
