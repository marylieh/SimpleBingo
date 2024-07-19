package de.marylieh.simplebingo.game

import de.marylieh.simplebingo.Manager
import de.marylieh.simplebingo.utils.lowerCase
import net.axay.kspigot.extensions.broadcast
import net.axay.kspigot.extensions.bukkit.feed
import net.axay.kspigot.extensions.bukkit.heal
import net.axay.kspigot.runnables.task
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.title.Title
import org.bukkit.*
import org.bukkit.entity.Firework
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue
import java.util.*
import java.util.logging.Level


object GameManager {

    private fun constantGameUpdate() {
        task(
            sync = true,
            delay = 0,
            period = 20,
            howOften = null
        ) {
            if (GamestateManager.currentGameState == GameStates.GAME_PHASE) {

                Bukkit.getOnlinePlayers().forEach { player: Player? ->

                    GamestateManager.bingoItems.forEach { material: Material ->

                        if (player?.hasMetadata(material.name) == false) {

                            if (player.inventory.containsAtLeast(ItemStack(material), 1)) {
                                player.setMetadata(material.name, FixedMetadataValue(Manager, material.name))
                                player.playSound(Sound.sound(Key.key("entity.player.levelup"), Sound.Source.MASTER, 1f, 1f))
                                player.sendMessage(Manager.prefix
                                    .append(Component.text("Du hast das Bingo Item ${material.name} gefunden.", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, false)))
                            }
                        }
                    }

                }

                Bukkit.getOnlinePlayers().forEach { player: Player ->
                    var found = 0

                    GamestateManager.bingoItems.forEach { material: Material ->

                        if (player.hasMetadata(material.name)) {
                            found += 1
                        }

                        if (found == GamestateManager.maxItems) {
                            initiateFinal(player)
                        }

                    }

                }

            }
        }
    }

    fun initiateGameUpdate() {
        generateItems()
        initiateCountdown()
    }

    fun initiateFinal(winner: Player?) {
        GamestateManager.currentGameState = GameStates.FINAL_PHASE
        GamestateManager.timerPaused = true

        if (!GamestateManager.countdownFinished) {
            broadcast(Manager.prefix
                .append(Component.text("${winner?.name} ", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, true))
                .append(Component.text("hat die Bingo Runde ", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, false))
                .append(Component.text("gewonnen!", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, true)))

            if (GamestateManager.timer) {
                broadcast(Manager.prefix
                    .append(Component.text("${winner?.name} ", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, true))
                    .append(Component.text("hat die Bingo Runde in ", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, false))
                    .append(Component.text("${TimerManager.stringifyTime(GamestateManager.timeInSeconds)} ", NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true))
                    .append(Component.text("abgeschlossen.", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, false)))
            }

            val titleComponent = Component.text("Gewonnen!", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, true)
            val subtitleComponent = Component.text("Du hast die Bingo Runde gewonnen, Glückwunsch!", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, false)
            val title = Title.title(titleComponent, subtitleComponent)

            @Suppress("NAME_SHADOWING")
            val winner = winner as Player
            GamestateManager.winner = winner.name

            winner.showTitle(title)

            playFireworks(winner)
        }

        if (GamestateManager.countdownFinished) {

            if (GamestateManager.countdownHighestCountWin) {
                val finishedItems: MutableMap<UUID, Int> = mutableMapOf()

                Bukkit.getOnlinePlayers().forEach {
                    finishedItems[it.uniqueId] = GamestateManager.countFoundItems(it)
                }

                val highestItemCount = finishedItems.maxByOrNull { it.value }

                if (highestItemCount == null) {
                    broadcast(Manager.prefix
                        .append(
                            Component.text("Die Bingo Runde ist vorbei. Die Zeit ist ", NamedTextColor.WHITE)
                                .decoration(TextDecoration.BOLD, false)
                        )
                        .append(Component.text("abgelaufen.", NamedTextColor.RED).decoration(TextDecoration.BOLD, true))
                        .append(
                            Component.text(
                                "Niemand hat es in der vorgegebenen Zeit geschafft alle Bingo Items zu sammeln.",
                                NamedTextColor.WHITE
                            ).decoration(TextDecoration.BOLD, false)
                        )
                    )
                } else {

                    val timeWinner = Bukkit.getPlayer(highestItemCount.key)
                    GamestateManager.winner = timeWinner!!.name
                    GamestateManager.timeWon = true
                    GamestateManager.itemsWon = highestItemCount.value

                    broadcast(
                        Manager.prefix
                            .append(
                                Component.text("Die Zeit ist ", NamedTextColor.WHITE)
                                    .decoration(TextDecoration.BOLD, false)
                            )
                            .append(
                                Component.text("abgelaufen.", NamedTextColor.RED).decoration(TextDecoration.BOLD, true)
                            )
                            .append(
                                Component.text("Jedoch hat ", NamedTextColor.WHITE)
                                    .decoration(TextDecoration.BOLD, false)
                            )
                            .append(
                                Component.text("${timeWinner.name} ", NamedTextColor.GREEN)
                                    .decoration(TextDecoration.BOLD, true)
                            )
                            .append(
                                Component.text("gewonnen.", NamedTextColor.WHITE).decoration(TextDecoration.BOLD, false)
                            )
                    )
                    playFireworks(timeWinner)

                }
            } else {
                broadcast(Manager.prefix
                        .append(
                            Component.text("Die Bingo Runde ist vorbei. Die Zeit ist ", NamedTextColor.WHITE)
                                .decoration(TextDecoration.BOLD, false)
                        )
                        .append(Component.text("abgelaufen. ", NamedTextColor.RED).decoration(TextDecoration.BOLD, true))
                        .append(
                            Component.text(
                                "Niemand hat es in der vorgegebenen Zeit geschafft alle Bingo Items zu sammeln.",
                                NamedTextColor.WHITE
                            ).decoration(TextDecoration.BOLD, false)
                        )
                )
            }
        }

        Bukkit.getOnlinePlayers().forEach {

            it.playSound(Sound.sound(Key.key("entity.wither.death"), Sound.Source.MASTER, 1f, 1f))
            it.gameMode = GameMode.SPECTATOR

            if (it.hasPermission("bingo.reset")) {
                it.sendMessage(Manager.prefix
                    .append(Component.text("Die Runde ist vorbei, verwende /reset um den Server zurückzusetzen.", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)))
            }
        }
    }

    private fun generateItems() {
        repeat(GamestateManager.maxItems) {
            var randomInt = (0..Material.entries.size).random()
            var item = Material.entries[randomInt]

            while (GamestateManager.excludedItems.lowerCase().contains(item.name.lowercase()) || item.name.startsWith("LEGACY")) {
                randomInt = (0..Material.entries.size).random()
                item = Material.entries[randomInt]
            }

            GamestateManager.bingoItems += item
        }
        Bukkit.getLogger().log(Level.INFO, "Die Bingo Items wurden ausgewählt: ${GamestateManager.bingoItems}")
    }

    private fun initiateCountdown() {
        var i = 10

        task(
            sync = true,
            delay = 0,
            period = 20,
            howOften = 10
        ) {
            Bukkit.getOnlinePlayers().forEach {
                val titleComponent = Component.text(i, NamedTextColor.WHITE)
                val subtitleComponent = Component.text("")
                val title = Title.title(titleComponent, subtitleComponent)

                it.showTitle(title)
                it.playSound(Sound.sound(Key.key("entity.arrow.hit_player"), Sound.Source.MASTER, 1f, 1f))
           }
            i -= 1

            if (i < 1) {
                val titleComponent = Component.text("Runde startet!", NamedTextColor.GREEN)
                val subtitleComponent = Component.text("Viel Glück!", NamedTextColor.GREEN)
                val title = Title.title(titleComponent, subtitleComponent)

                Bukkit.getOnlinePlayers().forEach {
                    it.showTitle(title)
                    it.playSound(Sound.sound(Key.key("entity.player.levelup"), Sound.Source.MASTER, 1f, 1f))
                    it.gameMode = GameMode.SURVIVAL
                    it.teleport(it.world.spawnLocation)
                    it.heal()
                    it.feed()
                    it.inventory.clear()
                    it.sendMessage(Manager.prefix
                        .append(Component.text("Verwende ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
                        .append(Component.text("/bingo ", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("um dir die aktuellen Bingo Items anzuzeigen.", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)))
                }
                if (GamestateManager.timer || GamestateManager.countdown) {
                    GamestateManager.timerPaused = false
                }
                GamestateManager.currentGameState = GameStates.GAME_PHASE
                constantGameUpdate()
            }
        }
    }

    private fun playFireworks(player: Player) {
        task(
            sync = true,
            delay = 0,
            period = 20,
            howOften = 10
        ) {
            val firework = player.world.spawn(
                player.location,
                Firework::class.java
            )
            val fireworkMeta = firework.fireworkMeta
            fireworkMeta.addEffects(FireworkEffect.builder()
                .flicker(true)
                .trail(true)
                .with(FireworkEffect.Type.BALL)
                .with(FireworkEffect.Type.BALL_LARGE)
                .withColor(Color.AQUA)
                .withColor(Color.YELLOW)
                .withColor(Color.RED)
                .withColor(Color.WHITE)
                .withColor(Color.GREEN)
                .build())

            fireworkMeta.power = 0
            firework.fireworkMeta = fireworkMeta
        }
    }
}