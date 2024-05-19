package de.marylieh.simplebingo.gui

import de.marylieh.simplebingo.Manager
import de.marylieh.simplebingo.game.GamestateManager
import de.marylieh.simplebingo.gui.guimanager.SettingsManager
import de.marylieh.simplebingo.gui.icons.SettingsIcons
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.input.awaitChatInput
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.gui.*
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class SettingsGUI {

    val settingsGUI = kSpigotGUI(GUIType.FOUR_BY_NINE) {
        title = literalText("Settings") {
            color = KColors.LIMEGREEN
        }
        page(1) {
            transitionFrom = PageChangeEffect.SLIDE_HORIZONTALLY
            transitionTo = PageChangeEffect.SLIDE_HORIZONTALLY

            placeholder(Slots.Border, itemStack(Material.WHITE_STAINED_GLASS_PANE) { meta { name = null } })
            button(Slots.RowThreeSlotTwo, SettingsIcons.timerIcon) { clickEvent ->

                (clickEvent.bukkitEvent.whoClicked as? Player)?.let { player ->
                    SettingsManager.switchTimer()
                    player.sendMessage(Manager.prefix
                        .append(Component.text("Der Timer ist nun ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                        .append(SettingsManager.timerStateComponent))
                    player.playSound(Sound.sound(Key.key("entity.arrow.hit_player"), Sound.Source.MASTER, 1f, 1f))
                    updateMainGUI(player)
                }
            }

            this.pageChanger(Slots.RowThreeSlotFour, SettingsIcons.countdownGUIIcon, 2, null, null)

        }

        page(2) {

            transitionFrom = PageChangeEffect.SLIDE_HORIZONTALLY
            transitionTo = PageChangeEffect.SLIDE_HORIZONTALLY

            placeholder(Slots.Border, itemStack(Material.WHITE_STAINED_GLASS_PANE) { meta { name = null } })

            placeholder(Slots.ColumnThree, itemStack(Material.WHITE_STAINED_GLASS_PANE) { meta { name = null } })
            placeholder(Slots.ColumnFour, itemStack(Material.WHITE_STAINED_GLASS_PANE) { meta { name = null } })
            placeholder(Slots.ColumnSix, itemStack(Material.WHITE_STAINED_GLASS_PANE) { meta { name = null } })
            placeholder(Slots.ColumnSeven, itemStack(Material.WHITE_STAINED_GLASS_PANE) { meta { name = null } })

            placeholder(Slots.RowTwoSlotFive, itemStack(Material.WHITE_STAINED_GLASS_PANE) { meta { name = null } })
            placeholder(Slots.RowTwoSlotTwo, itemStack(Material.WHITE_STAINED_GLASS_PANE) { meta { name = null } })

            button(Slots.RowThreeSlotTwo, SettingsIcons.addCountdownTimeIcon) { clickEvent ->

                (clickEvent.bukkitEvent.whoClicked as? Player)?.let { player ->
                    GamestateManager.timeInSeconds += 60
                    player.playSound(Sound.sound(Key.key("entity.arrow.hit_player"), Sound.Source.MASTER, 1f, 1f))
                    updateCountdownGUI(player)
                }

            }

            button(Slots.RowThreeSlotFive, SettingsIcons.countdownIcon) { clickEvent ->

                (clickEvent.bukkitEvent.whoClicked as? Player)?.let { player ->
                    SettingsManager.switchCountdown()
                    player.sendMessage(Manager.prefix
                        .append(Component.text("Der Countdown ist nun ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                        .append(SettingsManager.countdownStateComponent))
                    updateCountdownGUI(player)
                    player.playSound(Sound.sound(Key.key("entity.arrow.hit_player"), Sound.Source.MASTER, 1f, 1f))
                }

            }

            button(Slots.RowThreeSlotEight, SettingsIcons.reduceCountdownTimeIcon) { clickEvent ->

                (clickEvent.bukkitEvent.whoClicked as? Player)?.let { player ->
                    GamestateManager.timeInSeconds -= 60
                    player.playSound(Sound.sound(Key.key("entity.arrow.hit_player"), Sound.Source.MASTER, 1f, 1f))
                    updateCountdownGUI(player)
                }

            }

            button(Slots.RowTwoSlotFive, SettingsIcons.resetCountdownIcon) { clickEvent ->

                (clickEvent.bukkitEvent.whoClicked as? Player)?.let { player ->
                    GamestateManager.timerPaused = true
                    GamestateManager.timeInSeconds = 1800
                    player.sendMessage(Manager.prefix
                        .append(Component.text("Der Timer wurde ", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, false))
                        .append(Component.text("zurückgesetzt.", NamedTextColor.RED).decoration(TextDecoration.BOLD, false)))
                }

            }

            pageChanger(Slots.RowTwoSlotEight, SettingsIcons.backIcon, 1, null, null)
        }

        page(3) {
            transitionFrom = PageChangeEffect.SWIPE_HORIZONTALLY
            transitionTo = PageChangeEffect.SWIPE_HORIZONTALLY

            placeholder(Slots.Border, itemStack(Material.WHITE_STAINED_GLASS_PANE) { meta { name = null } })

            button(Slots.RowThreeSlotTwo, SettingsIcons.createTeamIcon) { clickEvent ->

                (clickEvent.bukkitEvent.whoClicked as? Player)?.let { player ->
                    player.awaitChatInput("Gebe den Namen des neuen Teams ein.") { name ->
                        val teamName = name.input as TextComponent
                        GamestateManager.tmp = teamName.content()
                    }

                    val compound = createRectCompound<Material>(
                        Slots.RowOneSlotOne, Slots.RowFourSlotEight,
                        iconGenerator = {
                            ItemStack(it)
                        },
                        onClick = { onClick, element ->
                            
                        }
                    )
                }

            }
        }
    }

    private fun updateCountdownGUI(player: Player) {
        player.openGUI(SettingsGUI().settingsGUI, 2)
    }

    private fun updateMainGUI(player: Player) {
        player.openGUI(SettingsGUI().settingsGUI, 1)
    }
}
