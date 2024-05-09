package de.marylieh.simplebingo.commands

import de.marylieh.simplebingo.Manager
import de.marylieh.simplebingo.game.GamestateManager
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.runs
import net.axay.kspigot.extensions.broadcast
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration

class PassiveCommand {

    val passiveCommand = command("passive") {
        runs {
            if (this.player.hasPermission("bingo.passive")) {

                if (GamestateManager.passive) {
                    GamestateManager.passive = false
                    broadcast(Manager.prefix
                        .append(Component.text("Der Passive Modus wurde von ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                        .append(Component.text("${this.player.name} ").color(NamedTextColor.GREEN))
                        .append(Component.text("deaktiviert").color(NamedTextColor.DARK_RED).decoration(TextDecoration.BOLD, true))
                        .append(Component.text(". Spieler bekommen jetzt wieder normal Schaden.", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)))

                    return@runs
                }

                GamestateManager.passive = true
                broadcast(Manager.prefix
                    .append(Component.text("Der Passive Modus wurde von ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                    .append(Component.text("${this.player.name} ").color(NamedTextColor.GREEN))
                    .append(Component.text("aktiviert").color(NamedTextColor.GREEN).decoration(TextDecoration.BOLD, true))
                    .append(Component.text(". Spieler bekommen jetzt keinen Schaden mehr durch Monster o.Ä.", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)))
            } else {
                this.player.sendMessage(Manager.insufficientPermissions)
            }
        }
    }

}
