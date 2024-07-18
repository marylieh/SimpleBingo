package de.marylieh.simplebingo.commands

import de.marylieh.simplebingo.Manager
import de.marylieh.simplebingo.gui.SettingsGUI
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.runs
import net.axay.kspigot.gui.openGUI

class SettingsCommand {

    val settingsCommand = command("settings") {
        runs {
            if (!(this.player.hasPermission("bingo.settings"))) {
                this.player.sendMessage(Manager.insufficientPermissions)
                return@runs
            }
            this.player.openGUI(SettingsGUI().settingsGUI)
        }
    }
}