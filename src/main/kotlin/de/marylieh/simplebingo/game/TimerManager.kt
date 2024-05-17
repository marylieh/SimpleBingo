package de.marylieh.simplebingo.game

import de.marylieh.simplebingo.Manager
import net.axay.kspigot.extensions.broadcast
import net.axay.kspigot.runnables.task
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit

object TimerManager {

    fun stringifyTime(time: Int): String {
        var duration = time
        var stringifiedTime = ""

        var hours = 0
        var minutes = 0
        var seconds = 0

        if (duration / 60 / 60 >= 1) {
            hours = duration / 60 / 60
            duration -= ((duration / 60 / 60) * 60 * 60)
        }

        if (duration / 60 >= 1) {
            minutes = duration / 60
            duration -= ((duration / 60) * 60)
        }

        if (duration >= 1) {
            seconds = duration
        }

        if (hours <= 9) {
            stringifiedTime = stringifiedTime + "0" + hours + ":"
        } else {
            stringifiedTime = "$stringifiedTime$hours:"
        }

        if (minutes <= 9) {
            stringifiedTime = stringifiedTime + "0" + minutes + ":"
        } else {
            stringifiedTime = "$stringifiedTime$minutes:"
        }

        if (seconds <= 9) {
            stringifiedTime = stringifiedTime + "0" + seconds
        } else {
            stringifiedTime += seconds
        }

        return stringifiedTime
    }
    
    private fun setActionbar() {
        if (GamestateManager.timer) {
            Bukkit.getOnlinePlayers().forEach {

                if (GamestateManager.timerPaused) {
                    it.sendActionBar(Component.text("Timer pausiert", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, true))
                    return
                }
                it.sendActionBar(Component.text(stringifyTime(GamestateManager.timeInSeconds), NamedTextColor.WHITE).decoration(TextDecoration.BOLD, true))
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
            setActionbar()

            if (GamestateManager.countdown) {
                GamestateManager.timeInSeconds -= 1
            } else {
                GamestateManager.timeInSeconds += 1
            }
        }

        if (GamestateManager.timeInSeconds < 1) {
            GamestateManager.endPhase = true

            broadcast(Manager.prefix
                .append(Component.text("Die Bingo Runde ist vorbei!", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, false)))
        }
    }

}