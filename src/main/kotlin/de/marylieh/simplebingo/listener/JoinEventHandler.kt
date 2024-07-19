package de.marylieh.simplebingo.listener

import de.marylieh.simplebingo.Manager
import net.axay.kspigot.event.listen
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.event.player.PlayerJoinEvent

class JoinEventHandler {

    val joinEventHandler = listen<PlayerJoinEvent> { event ->
        event.joinMessage(Manager.prefix
            .append(Component.text(">> ", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, true))
            .append(Component.text(event.player.name, NamedTextColor.WHITE).decoration(TextDecoration.BOLD, false)))
    }
}