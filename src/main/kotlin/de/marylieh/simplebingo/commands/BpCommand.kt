package de.marylieh.simplebingo.commands

import de.marylieh.simplebingo.Manager
import de.marylieh.simplebingo.game.BackpackManager
import de.marylieh.simplebingo.game.GameStates
import de.marylieh.simplebingo.game.GamestateManager
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.runs
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration

class BpCommand {

    val bpCommand = command("bp") {
        runs {
            if (!GamestateManager.backpackEnabled) {
                this.player.sendMessage(Manager.prefix
                    .append(Component.text("Das Backpack wurde global ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                    .append(Component.text("deaktiviert", NamedTextColor.DARK_RED).decoration(TextDecoration.BOLD, true))
                    .append(Component.text(".", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)))
                return@runs
            }

            if (GamestateManager.currentGameState != GameStates.GAME_PHASE) {
                this.player.sendMessage(Manager.prefix
                    .append(Component.text("Die Bingo Runde wurde noch nicht gestartet.", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)))
            }

            this.player.openInventory(BackpackManager.getBackpack(player))

        }
    }
}