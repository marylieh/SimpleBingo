package de.marylieh.simplebingo.gui

import de.marylieh.simplebingo.Manager
import de.marylieh.simplebingo.game.GameManager
import de.marylieh.simplebingo.game.GameStates
import de.marylieh.simplebingo.game.GamestateManager
import de.marylieh.simplebingo.gui.guimanager.SettingsManager
import de.marylieh.simplebingo.gui.icons.SettingsIcons
import de.marylieh.simplebingo.teams.Team
import de.marylieh.simplebingo.teams.TeamManager
import de.marylieh.simplebingo.teams.teamMaterials
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.input.awaitChatInput
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.gui.*
import net.axay.kspigot.items.*
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
        title = literalText("Settings | BETA") {
            color = KColors.DARKBLUE
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
            // this.pageChanger(Slots.RowThreeSlotSix, SettingsIcons.teamsIcon, 3, null, null)
            button(Slots.RowThreeSlotSix, SettingsIcons.teamsComingSoonIcon) { clickEvent ->
                (clickEvent.bukkitEvent.whoClicked as? Player)?.let { player ->
                    player.closeInventory()
                    player.sendMessage(Manager.prefix
                        .append(Component.text("Dieses Feature wird demnächst via auto update hinzugefügt :)", NamedTextColor.RED).decoration(TextDecoration.BOLD, false)))
                }
            }

            button(Slots.RowThreeSlotEight, SettingsIcons.startIcon) { guiClickEvent ->

                (guiClickEvent.bukkitEvent.whoClicked as? Player)?.let { player ->
                    if (!(player.hasPermission("bingo.start"))) {
                        player.closeInventory()
                        player.sendMessage(Manager.insufficientPermissions)
                    }

                    if (GamestateManager.currentGameState != GameStates.LOBBY_PHASE) {
                        player.sendMessage(Manager.prefix
                            .append(Component.text("Die Instanz befindet sich nicht in der Lobby Phase, führe /reset aus um den Server zu reinitialisieren.", NamedTextColor.RED).decoration(TextDecoration.BOLD, false)))
                    }

                    player.sendMessage(Manager.prefix
                        .append(Component.text("Die Bingo Runde wird ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                        .append(Component.text("gestartet.", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, true)))
                    player.closeInventory()
                    GameManager.initiateGameUpdate()
                }

            }

            if (GamestateManager.debug) {
                button(Slots.RowFourSlotTwo, ItemStack(Material.CHERRY_CHEST_BOAT)) {clickEvent ->
                    (clickEvent.bukkitEvent.whoClicked as? Player)?.let { player ->
                        player.openInventory(GamestateManager.getBingoInventory(player))
                    }
                }
            }

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
                    updateTeamColorGUI(player)
                }

            }

            // TODO: Implement team GUI here
            button(Slots.RowThreeSlotFour, SettingsIcons.teamsComingSoonIcon) { clickEvent ->

                (clickEvent.bukkitEvent.whoClicked as? Player)?.sendMessage(Manager.prefix
                    .append(Component.text("Dieses Feature wird demnächst via auto update hinzugefügt :)", NamedTextColor.RED).decoration(TextDecoration.BOLD, false)))

            }
        }
        page(4) {
            val compound = createRectCompound<Material>(
                Slots.RowOneSlotOne, Slots.RowFourSlotEight,
                iconGenerator = {
                    ItemStack(it)
                },
                onClick = { clickEvent, element ->
                    //clickEvent.player.closeInventory()
                    // TODO: Fix chat input
                    clickEvent.player.awaitChatInput("Gebe den Namen des neuen Teams ein.") { name ->
                        val teamName = name.input as TextComponent
                        GamestateManager.tmp = teamName.content()

                        TeamManager.createTeam(GamestateManager.tmp, element)
                        clickEvent.player.sendMessage(Manager.prefix
                            .append(Component.text("Das Team ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                            .append(Component.text(GamestateManager.tmp, NamedTextColor.WHITE).decoration(TextDecoration.BOLD, true))
                            .append(Component.text(" wurde erstellt.", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)))
                        GamestateManager.tmp = ""

                        while (teamName.content().isBlank()) {
                            return@awaitChatInput
                        }
                    }
                }
            )
            compound.addContent(teamMaterials)
            compound.sortContentBy { it.name }
            compoundScroll(
                Slots.RowOneSlotNine,
                ItemStack(Material.PAPER),
                compound,
                scrollTimes = 4
            )
            compoundScroll(
                Slots.RowFourSlotNine,
                ItemStack(Material.PAPER),
                compound,
                scrollTimes = 4,
                reverse = true
            )
        }
        page(5) {
            transitionFrom = PageChangeEffect.SLIDE_VERTICALLY
            transitionTo = PageChangeEffect.SLIDE_VERTICALLY

            val compound = createRectCompound<Team>(
                Slots.RowOneSlotOne, Slots.RowFourSlotEight,
                iconGenerator = {
                    itemStack(it.color) {
                        setMeta {
                            name = literalText(it.name) {
                                color = KColors.WHITE
                                italic = false
                                bold = true
                            }
                            addLore {
                                +literalText("Mitglieder: ${it.members.size}") {
                                    color = KColors.AQUA
                                    italic = false
                                }
                                +literalText(" ")
                                +literalText("Klicke, um das Team zu bearbeiten.") {
                                    color = KColors.GRAY
                                    italic = true
                                }
                            }
                        }
                    }
                },
                onClick = { clickEvent, element ->
                    // TODO: Open specific team management ui
                }
            )
            compound.sortContentBy { it.name }
        }
    }

    private fun updateCountdownGUI(player: Player) {
        player.openGUI(SettingsGUI().settingsGUI, 2)
    }

    private fun updateMainGUI(player: Player) {
        player.openGUI(SettingsGUI().settingsGUI, 1)
    }

    private fun updateTeamColorGUI(player: Player) {
        player.openGUI(SettingsGUI().settingsGUI, 4)
    }

    @Suppress("UNUSED")
    private fun updateTeamManagementGUI(player: Player) {
        player.openGUI(SettingsGUI().settingsGUI, 5)
    }
}
