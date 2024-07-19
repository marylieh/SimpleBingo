package de.marylieh.simplebingo.listener

import de.marylieh.simplebingo.Manager
import net.axay.kspigot.event.listen
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.event.player.PlayerQuitEvent

class LeaveEventHandler {

    val leaveEventHandler = listen<PlayerQuitEvent> { event ->
        event.quitMessage(Manager.prefix
            .append(Component.text("<< ", NamedTextColor.RED).decoration(TextDecoration.BOLD, true))
            .append(Component.text(event.player.name, NamedTextColor.WHITE).decoration(TextDecoration.BOLD, false)))
    }
}