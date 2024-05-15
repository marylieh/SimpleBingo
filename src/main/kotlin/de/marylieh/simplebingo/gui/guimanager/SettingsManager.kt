package de.marylieh.simplebingo.gui.guimanager

import de.marylieh.simplebingo.game.GamestateManager
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.kyori.adventure.text.Component

object SettingsManager {

    fun switchTimer() {
        if (GamestateManager.timer) {
            GamestateManager.timer = false
            return
        }
        GamestateManager.timer = true
    }

    fun switchCountdown() {
        if (GamestateManager.timer) {
            GamestateManager.timer = false
        }

        if (GamestateManager.countdown) {
            GamestateManager.countdown = false
            return
        }
        GamestateManager.countdown = true
    }

    val timerStateComponent: Component
        get() {
            if (GamestateManager.timer) {
                return literalText("Aktiviert") {
                    color = KColors.LIMEGREEN
                    bold = true
                }
            }
            return literalText("Deaktiviert") {
                color = KColors.RED
                bold = true
            }
        }

    val countdownStateComponent: Component
        get() {
            if (GamestateManager.countdown) {
                return literalText("Aktiviert") {
                    color = KColors.LIMEGREEN
                    bold = true
                }
            }
            return literalText("Deaktiviert") {
                color = KColors.RED
                bold = true
            }
        }
}
