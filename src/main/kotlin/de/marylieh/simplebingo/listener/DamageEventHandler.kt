package de.marylieh.simplebingo.listener

import de.marylieh.simplebingo.game.GameStates
import de.marylieh.simplebingo.game.GamestateManager
import net.axay.kspigot.event.listen
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.EntityDamageEvent

class DamageEventHandler {

    val damageEventHandler = listen<EntityDamageEvent> { event ->
        if (GamestateManager.passive && event.entity.type == EntityType.PLAYER) {
            event.isCancelled = true
        }

        if (GamestateManager.currentGameState == GameStates.LOBBY_PHASE) {
            event.isCancelled = true
        }
    }
}