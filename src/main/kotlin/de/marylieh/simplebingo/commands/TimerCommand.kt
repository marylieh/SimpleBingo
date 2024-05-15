package de.marylieh.simplebingo.commands

import de.marylieh.simplebingo.Manager
import de.marylieh.simplebingo.game.GamestateManager
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.literal
import net.axay.kspigot.commands.runs
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration

class TimerCommand {

    val timerCommand = command("timer") {
        literal("resume") {
            runs {
                if (!(this.player.hasPermission("bingo.timer"))) {
                    this.player.sendMessage(Manager.insufficientPermissions)
                    return@runs
                }
                GamestateManager.timerPaused = false
                this.player.sendMessage(Manager.prefix
                    .append(Component.text("Der Timer wurde ", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, false))
                    .append(Component.text("fortgesetzt.", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, false)))
            }
        }
        literal("pause") {
            runs {
                if (!(this.player.hasPermission("bingo.timer"))) {
                    this.player.sendMessage(Manager.insufficientPermissions)
                    return@runs
                }
                GamestateManager.timerPaused = false
                this.player.sendMessage(Manager.prefix
                    .append(Component.text("Der Timer wurde ", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, false))
                    .append(Component.text("pausiert.", NamedTextColor.GOLD).decoration(TextDecoration.BOLD, false)))
            }
        }
        literal("reset") {
            runs {
                if (!(this.player.hasPermission("bingo.timer"))) {
                    this.player.sendMessage(Manager.insufficientPermissions)
                    return@runs
                }
                GamestateManager.timerPaused = true
                GamestateManager.timeInSeconds = 0
                this.player.sendMessage(Manager.prefix
                    .append(Component.text("Der Timer wurde ", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, false))
                    .append(Component.text("zurückgesetzt.", NamedTextColor.RED).decoration(TextDecoration.BOLD, false)))
            }
        }
    }

}