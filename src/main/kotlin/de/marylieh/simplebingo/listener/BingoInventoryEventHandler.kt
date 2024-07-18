package de.marylieh.simplebingo.listener

import net.axay.kspigot.event.listen
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.event.inventory.InventoryClickEvent

class BingoInventoryEventHandler {

    val bingoInventoryEventHandler = listen<InventoryClickEvent> { event ->
        if (event.whoClicked.openInventory.title() == Component.text("Bingo | Items", NamedTextColor.GREEN)) {
            event.isCancelled = true
        }
    }
}