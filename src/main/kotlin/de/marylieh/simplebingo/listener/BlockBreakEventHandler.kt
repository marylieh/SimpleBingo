package de.marylieh.simplebingo.listener

import de.marylieh.simplebingo.game.GameStates
import de.marylieh.simplebingo.game.GamestateManager
import net.axay.kspigot.event.listen
import org.bukkit.event.block.BlockBreakEvent

class BlockBreakEventHandler {

    val blockBreakEventHandler = listen<BlockBreakEvent> { event ->
        if (GamestateManager.currentGameState == GameStates.LOBBY_PHASE) {
            event.isCancelled = true
        }
    }
}