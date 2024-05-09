package de.marylieh.simplebingo.gui

import de.marylieh.simplebingo.Manager
import de.marylieh.simplebingo.gui.guimanager.SettingsManager
import de.marylieh.simplebingo.gui.icons.SettingsIcons
import net.axay.kspigot.gui.GUIType
import net.axay.kspigot.gui.Slots
import net.axay.kspigot.gui.kSpigotGUI
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.inventory.ItemStack

object SettingsGUI {

//    val settingsGUI = kSpigotGUI(GUIType.SIX_BY_NINE) {
//        title = literalText("Settings") {
//            color = KColors.LIMEGREEN
//        }
//        page(1) {
//            placeholder(Slots.Border, itemStack(Material.WHITE_STAINED_GLASS_PANE) { meta { name = null } })
//            button(Slots.RowFiveSlotTwo, SettingsIcons.timerIcon) { clickEvent ->
//
//                (clickEvent.bukkitEvent.whoClicked as? Player)?.let { player ->
//                    SettingsManager.switchTimer()
//                    player.sendMessage(Manager.prefix
//                        .append(Component.text("Der Timer ist nun ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
//                        .append(SettingsManager.timerStateComponent))
//                }
//            }
//        }
//    }

    val settingsGUI = kSpigotGUI(GUIType.SIX_BY_NINE) {
        page(1) {
            val compound = createRectCompound<ItemStack>(
                Slots.RowFiveSlotTwo, Slots.RowFiveSlotTwo,
                iconGenerator = {
                    ItemStack(it)
                },
                onClick = { clickEvent, element ->
                    clickEvent.bukkitEvent.isCancelled = true
                    SettingsManager.switchTimer()
                    clickEvent.player.sendMessage(Manager.prefix
                        .append(Component.text("Der Timer ist nun ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                        .append(SettingsManager.timerStateComponent))
                    clickEvent.player.inventory.addItem(SettingsIcons.timerIcon)
                }
            )
            compound.addContent(SettingsIcons.timerIcon)
        }
    }
}
