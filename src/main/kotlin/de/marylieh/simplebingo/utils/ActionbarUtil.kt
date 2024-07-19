package de.marylieh.simplebingo.utils

import de.marylieh.simplebingo.game.GameStates
import de.marylieh.simplebingo.game.GamestateManager
import de.marylieh.simplebingo.game.TimerManager
import net.axay.kspigot.runnables.task
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit

object ActionbarUtil {

    private fun sendActionbar() {
        if (GamestateManager.currentGameState == GameStates.LOBBY_PHASE) {
            Bukkit.getOnlinePlayers().forEach {
                it.sendActionBar(Component.text("BINGO | LOBBY PHASE | ${Bukkit.getOnlinePlayers().size} Spieler", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, true))
            }
        }

        if (GamestateManager.currentGameState == GameStates.GAME_PHASE) {
            if (!GamestateManager.countdown && !GamestateManager.timer) {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendActionBar(Component.text("BINGO | Items: ${GamestateManager.countFoundItems(it)}/${GamestateManager.maxItems}").decoration(TextDecoration.BOLD, true))
                }
            }
        }

        if (GamestateManager.currentGameState == GameStates.FINAL_PHASE) {
            if (GamestateManager.timer) {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendActionBar(Component.text("BINGO | Gewinner: ${GamestateManager.winner} | Zeit: ${TimerManager.stringifyTime(GamestateManager.timeInSeconds)}", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, true))
                }
            }
            if (GamestateManager.countdownFinished && !GamestateManager.timeWon) {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendActionBar(Component.text("BINGO | Kein Gewinner | Zeit abgelaufen", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, true))
                }
            }

            if (GamestateManager.countdownFinished && GamestateManager.timeWon) {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendActionBar(Component.text("BINGO | Gewinner: ${GamestateManager.winner} | Items: ${GamestateManager.itemsWon}/${GamestateManager.maxItems}").decoration(TextDecoration.BOLD, true))
                }
            }

            if (!GamestateManager.countdown && !GamestateManager.timer) {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendActionBar(Component.text("BINGO | Gewinner: ${GamestateManager.winner}").decoration(TextDecoration.BOLD, true))
                }
            }
        }
    }

    fun runTask() {
        task(
            sync = true,
            delay = 0,
            period = 20,
            howOften = null
        ) {
            sendActionbar()
        }
    }
}