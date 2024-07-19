package de.marylieh.simplebingo.commands

import de.marylieh.simplebingo.Manager
import de.marylieh.simplebingo.game.GameManager
import de.marylieh.simplebingo.game.GameStates
import de.marylieh.simplebingo.game.GamestateManager
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.runs
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration

class StartCommand {

    val startCommand = command("start") {
        runs {
            if (!(this.player.hasPermission("bingo.start"))) {
                this.player.sendMessage(Manager.insufficientPermissions)
                return@runs
            }

            if (GamestateManager.currentGameState != GameStates.LOBBY_PHASE) {
                this.player.sendMessage(Manager.prefix
                    .append(Component.text("Die Instanz befindet sich nicht in der Lobby Phase, führe /reset aus um den Server zu reinitialisieren.", NamedTextColor.RED).decoration(TextDecoration.BOLD, false)))
                return@runs
            }

            this.player.sendMessage(Manager.prefix
                .append(Component.text("Die Bingo Runde wird ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                .append(Component.text("gestartet.", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, true)))

            GameManager.initiateGameUpdate()
        }
    }
}