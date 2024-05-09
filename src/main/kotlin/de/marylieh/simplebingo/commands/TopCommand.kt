package de.marylieh.simplebingo.commands

import de.marylieh.simplebingo.Manager
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.runs
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Location

class TopCommand {

    val topCommand = command("top") {
        runs {
            if (this.player.world.name.equals("world_nether", true)) {
                val worldSpawn = Bukkit.getWorld("world")!!.spawnLocation
                this.player.teleport(worldSpawn)
                this.player.sendMessage(Manager.prefix
                    .append(Component.text("Du wurdest an den Welt Spawn teleportiert.", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)))
                return@runs
            }
            val world = this.player.world
            val x = this.player.location.blockX
            val z = this.player.location.blockZ

            val y = this.player.world.getHighestBlockYAt(x, z)
            val newLocation = Location(world, x.toDouble(), y.toDouble(), z.toDouble())
            this.player.teleport(newLocation)

            this.player.sendMessage(Manager.prefix
                .append(Component.text("Du wurdest an die ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                .append(Component.text("höchste Stelle ", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, true))
                .append(Component.text("teleportiert.", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)))
        }
    }
}