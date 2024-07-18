package de.marylieh.simplebingo

import de.marylieh.simplebingo.commands.*
import de.marylieh.simplebingo.config.ConfigManager
import de.marylieh.simplebingo.game.GamestateManager
import de.marylieh.simplebingo.game.TimerManager
import de.marylieh.simplebingo.listener.*
import de.marylieh.simplebingo.utils.ActionbarUtil
import net.axay.kspigot.commands.register
import net.axay.kspigot.event.register
import net.axay.kspigot.extensions.broadcast
import net.axay.kspigot.main.KSpigot
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import java.util.logging.Level

class InternalMainClass : KSpigot() {

    companion object {
        lateinit var INSTANCE: InternalMainClass; private set
    }

    override fun load() {
        INSTANCE = this
    }

    override fun startup() {
        registerCommands()
        listenerRegistration()
        this.saveDefaultConfig()
        ConfigManager.config()
        TimerManager.runTask()

        checkReload()
        checkConfig()
        ActionbarUtil.runTask()
    }

    override fun shutdown() {
        ConfigManager.save()
    }

    private fun registerCommands() {
        PassiveCommand().passiveCommand.register()
        TopCommand().topCommand.register()
        ReloadConfigCommand().reloadConfigCommand.register()
        BpCommand().bpCommand.register()
        SettingsCommand().settingsCommand.register()
        TimerCommand().timerCommand.register()
        BingoCommand().bingoCommand.register()
        ResetCommand().resetCommand.register()
        StartCommand().startCommand.register()
    }

    private fun listenerRegistration() {
        JoinEventHandler().joinEventHandler.register()
        LeaveEventHandler().leaveEventHandler.register()
        DamageEventHandler().damageEventHandler.register()
        BingoInventoryEventHandler().bingoInventoryEventHandler.register()
        BlockBreakEventHandler().blockBreakEventHandler.register()
        HungerEventHandler().hungerEventHandler.register()
    }

    private fun checkReload() {
        if (Bukkit.getOnlinePlayers().isNotEmpty()) {
            broadcast(Manager.prefix
                .append(Component.text("Es sieht so aus als währe der Server mit dem /reload command neu geladen worden. ACHTUNG: Dies WIRD zu Problemen mit dem Bingo plugin führen!", NamedTextColor.RED)))
        }
    }

    private fun checkConfig() {
        if (GamestateManager.maxItems % 9 != 0) {
            Bukkit.getLogger().log(Level.SEVERE, "maxItems in der config ist nicht durch 9 Teilbar. Bitte verwende ein vielfaches von 9. Das plugin wird jetzt aus Sicherheitsgründen deaktiviert.")
            Bukkit.getPluginManager().disablePlugin(Manager)
        }

        if (GamestateManager.backpackSlots % 9 != 0) {
            Bukkit.getLogger().log(Level.SEVERE, "command.backpack.slots in der config ist nicht durch 9 Teilbar. Bitte verwende ein vielfaches von 9. Das plugin wird jetzt aus Sicherheitsgründen deaktiviert.")
            Bukkit.getPluginManager().disablePlugin(Manager)
        }
    }

    val prefix =
        Component.text("BINGO ", NamedTextColor.WHITE)
        .decoration(TextDecoration.BOLD, true)
        .append(Component.text("|", NamedTextColor.DARK_GRAY))
        .append(Component.text(" ").color(NamedTextColor.GRAY))

    val insufficientPermissions =
        prefix.append(Component.text("Dieser Befehl erfordert ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)
            .append(Component.text("erhöhte Berechtigungen").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, true))
            .append(Component.text(".", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)))

}

val Manager by lazy { InternalMainClass.INSTANCE }
