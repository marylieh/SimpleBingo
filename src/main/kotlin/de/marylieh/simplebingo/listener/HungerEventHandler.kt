package de.marylieh.simplebingo.listener

import de.marylieh.simplebingo.game.GameStates
import de.marylieh.simplebingo.game.GamestateManager
import net.axay.kspigot.event.listen
import org.bukkit.event.entity.FoodLevelChangeEvent

class HungerEventHandler {

    val hungerEventHandler = listen<FoodLevelChangeEvent> { event ->
        if (GamestateManager.currentGameState == GameStates.LOBBY_PHASE || GamestateManager.passive) {
            event.isCancelled = true
        }
    }
}