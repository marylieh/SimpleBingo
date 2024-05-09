package de.marylieh.simplebingo.commands

import de.marylieh.simplebingo.Manager
import de.marylieh.simplebingo.config.ConfigManager
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.runs
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration

class ReloadConfigCommand {

    val reloadConfigCommand = command("bingorlconfig") {
        runs {
            if (!(this.player.hasPermission("bingo.rlconfig"))) {
                this.player.sendMessage(Manager.insufficientPermissions)
                return@runs
            }

            val reloadConfig = ConfigManager.reload()

            if (reloadConfig) {
                this.player.sendMessage(Manager.prefix
                    .append(Component.text("Das Laden der Konfiguration war erfolgreich!", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, false)))
                return@runs
            }
            this.player.sendMessage(Manager.prefix
                .append(Component.text("Beim Laden der Konfiguration ist ein Fehler aufgetreten. Bitte überprüfe die Konsolenausgabe für weitere Informationen zur Fehlerbehebung.", NamedTextColor.RED).decoration(TextDecoration.BOLD, false)))
        }
    }
}