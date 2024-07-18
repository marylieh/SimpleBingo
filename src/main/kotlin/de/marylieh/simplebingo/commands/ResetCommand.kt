package de.marylieh.simplebingo.commands

import de.marylieh.simplebingo.Manager
import de.marylieh.simplebingo.game.ResetManager
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.literal
import net.axay.kspigot.commands.runs
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import java.util.logging.Level

class ResetCommand {

    val resetCommand = command("reset") {
        literal("confirm") {
            runs {
                if (!(this.player.hasPermission("bingo.reset"))) {
                    this.player.sendMessage(Manager.insufficientPermissions)
                    return@runs
                }

                Bukkit.getLogger().log(Level.INFO, "Spieler werden gekickt..")

                Bukkit.getOnlinePlayers().forEach {
                    it.kick(Component.text("Die Welt wurde von ", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, false)
                        .append(Component.text("${this.player.name} ", NamedTextColor.BLUE).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("zurückgesetzt. Du wirst den Server in einigen Minuten wieder betreten können.", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, false)))
                }

                Bukkit.getLogger().log(Level.INFO, "Lösche welt: world")
                ResetManager.resetWorld(ResetManager.world)

                Bukkit.getLogger().log(Level.INFO, "Lösche welt: world_nether")
                ResetManager.resetWorld(ResetManager.worldNether)

                Bukkit.getLogger().log(Level.INFO, "Lösche welt: world_the_end")
                ResetManager.resetWorld(ResetManager.worldEnd)

                Bukkit.getLogger().log(Level.WARNING, "Der Server wird jetzt neu gestartet, eventuell muss der Server manuell neu gestartet werden" +
                        " sofern keine automatische neustart Routine im Server start Skript eingebaut wurde.")
                Bukkit.shutdown()
            }
        }
        runs {
            if (!(this.player.hasPermission("bingo.reset"))) {
                this.player.sendMessage(Manager.insufficientPermissions)
                return@runs
            }

            this.player.sendMessage(Manager.prefix
                .append(Component.text("Verwende ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                .append(Component.text("/reset confirm ", NamedTextColor.RED).decoration(TextDecoration.BOLD, false))
                .append(Component.text("um den Server zurückzusetzen und eine neue Bingo Runde starten zu können. Dabei werden die folgenden komponenten ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                .append(Component.text("gelöscht und re-generiert: ", NamedTextColor.DARK_RED).decoration(TextDecoration.BOLD, true))
                .append(Component.text("Standard Welt (world), Nether (world_nether), End (world_the_end).", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)))
        }
    }
}